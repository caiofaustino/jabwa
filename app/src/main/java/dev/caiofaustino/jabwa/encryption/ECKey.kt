package dev.caiofaustino.jabwa.encryption

import org.bouncycastle.crypto.AsymmetricCipherKeyPair
import org.bouncycastle.crypto.ec.CustomNamedCurves
import org.bouncycastle.crypto.generators.ECKeyPairGenerator
import org.bouncycastle.crypto.params.ECDomainParameters
import org.bouncycastle.crypto.params.ECKeyGenerationParameters
import org.bouncycastle.crypto.params.ECPrivateKeyParameters
import org.bouncycastle.math.ec.ECPoint
import org.bouncycastle.math.ec.FixedPointCombMultiplier
import org.bouncycastle.math.ec.FixedPointUtil
import java.math.BigInteger
import java.security.SecureRandom

class ECKey(private val privateKeyInternal: BigInteger?, private val publicKeyInternal: ECPoint) {

    val privateKey: ByteArray
    val publicKey: ByteArray

    val isPubOnly: Boolean = privateKeyInternal == null

    constructor(privateKey: ByteArray) : this(BigInteger(1, privateKey))

    constructor (privateKey: BigInteger) : this(privateKey, publicPointFromPrivate(privateKey))

    constructor (publicKey: ECPoint) : this (null, publicKey)

    init {
        privateKey = if (privateKeyInternal != null) {
            // Try and catch buggy callers or bad key imports, etc.
            if (!checkKey(privateKeyInternal)) {
                throw RuntimeException("Invalid Private Key")
            }

            privateKeyInternal.toUnsignedByteArray()
        } else {
            ByteArray(0)
        }

        publicKey = publicKeyInternal.getEncoded(false)

        // TODO: HALF_CURVE_ORDER - don't know what this is
        // If "pub" is set but not "priv", we can only verify signatures, not make them.
    }

    /**
     * Check if private key is valid value.
     */
    private fun checkKey(key: BigInteger): Boolean {
        // Zero and one are special because these are often used as sentinel values and because scripting languages have a habit of auto-casting true and false to
        // 1 and 0 or vice-versa. Type confusion bugs could therefore result in private keys with these values.
        val expectedBitLength = 32 * 8 // 32 bytes
        return key.bitLength() <= expectedBitLength && key != BigInteger.ZERO && key != BigInteger.ONE
    }

//    /**
//     * Will automatically adjust the S component to be less than or equal to half the curve order, if necessary.
//     * This is required because for every signature (r,s) the signature (r, -s (mod N)) is a valid signature of
//     * the same message. However, we dislike the ability to modify the bits of a Bitcoin transaction after it's
//     * been signed, as that violates various assumed invariants. Thus in future only one of those forms will be
//     * considered legal and the other will be banned.
//     */
//    private fun ensureCanonical() {
//
//        val halfCurveOrder = curve.n.shiftRight(1)
//
//        if (s.compareTo(com.google.bitcoin.core.ECKey.HALF_CURVE_ORDER) > 0) {
//            // The order of the curve is the number of valid points that exist on that curve. If S is in the upper
//            // half of the number of valid points, then bring it back to the lower half. Otherwise, imagine that
//            //    N = 10
//            //    s = 8, so (-8 % 10 == 2) thus both (r, 8) and (r, 2) are valid solutions.
//            //    10 - 8 == 2, giving us always the latter solution, which is canonical.
//            s = com.google.bitcoin.core.ECKey.CURVE.getN().subtract(s)
//        }
//    }

    companion object {

        // Bitcoin's elliptic curve
        private const val secp256k1CurveName = "secp256k1"

        // The parameters of the secp256k1 curve that Bitcoin uses.
        private val secp256k1Parameters = CustomNamedCurves.getByName(secp256k1CurveName)

        private val curve: ECDomainParameters

        init {
            // Tell Bouncy Castle to precompute data that's needed during secp256k1 calculations.
            FixedPointUtil.precompute(secp256k1Parameters.g)

            curve = ECDomainParameters(
                secp256k1Parameters.curve,
                secp256k1Parameters.g,
                secp256k1Parameters.n,
                secp256k1Parameters.h
            )
        }

        // TODO Probably not really secure because of SecureRandom, just checking.
        fun generate(): ECKey {
            val secureRandom = SecureRandom()
            val generator: ECKeyPairGenerator = ECKeyPairGenerator()
            val keygenParams: ECKeyGenerationParameters = ECKeyGenerationParameters(curve, secureRandom)
            generator.init(keygenParams)
            val keyPair: AsymmetricCipherKeyPair = generator.generateKeyPair()
            val privateParams: ECPrivateKeyParameters = keyPair.private as ECPrivateKeyParameters
//            val publicParams: ECPublicKeyParameters = keyPair.public as ECPublicKeyParameters

            // TODO: missing from bitcoinj
            //  Point compression is used so the resulting public key will be 33 bytes (32 for the co-ordinate and 1 byte to represent the y bit).
            return ECKey(privateParams.d)
        }

        /**
         * Returns public key point from the given private key.
         * Uses FixedPointCombMultiplier (FPCM) for side-channel protection
         */
        private fun publicPointFromPrivate(privateKey: BigInteger): ECPoint {
            // FixedPointCombMultiplier currently doesn't support scalars longer than the group order,
            // but that could change in future versions of Bouncy Castle.

            return if (privateKey.bitLength() > curve.n.bitLength()) {
                FixedPointCombMultiplier().multiply(curve.g, privateKey.mod(curve.n))
            } else {
                FixedPointCombMultiplier().multiply(curve.g, privateKey)
            }

            // The scalar multiplication involves
            // the private key as the scalar value, so it's important to treat that as
            // a secret. FPCM avoids any branching or cache-dependent timing relating
            // to the scalar value, so is suitable here.

            // It's also better performance when there are many keys, since FPCM
            // assumes the point will be used for many calculations and therefore can
            // perform a large precomputation to speed up the individual scalar
            // multiplications.
        }
    }
}