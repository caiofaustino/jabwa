package dev.caiofaustino.jabwa.encryption

import org.bouncycastle.crypto.AsymmetricCipherKeyPair
import org.bouncycastle.crypto.ec.CustomNamedCurves
import org.bouncycastle.crypto.generators.ECKeyPairGenerator
import org.bouncycastle.crypto.params.ECDomainParameters
import org.bouncycastle.crypto.params.ECKeyGenerationParameters
import org.bouncycastle.crypto.params.ECPrivateKeyParameters
import org.bouncycastle.crypto.params.ECPublicKeyParameters
import org.bouncycastle.math.ec.FixedPointCombMultiplier
import org.bouncycastle.math.ec.FixedPointUtil
import org.junit.Assert
import org.junit.Test
import java.math.BigInteger
import java.security.SecureRandom

class ECKeyTest {

    @Test
    fun test_playground() {
        // This is just a sanity test and a playground to mess with the class

        val bigInteger = BigInteger("1234567890", 10)
        val bigIntKey = ECKey(bigInteger)

        Assert.assertFalse(bigIntKey.isPubOnly);

        val byteArray = "1234567890".toByteArray()
        val biteArrayKey = ECKey(byteArray)

        Assert.assertFalse(biteArrayKey.isPubOnly);
    }

    @Test
    fun test_public_key_only() {
        println("test_public_key_only")

        val pubOnlyKey = ECKey(generateRandomPublic().q)

        assert(pubOnlyKey.isPubOnly);
    }

    @Test
    fun test_private_key_not_public_only() {
        println("test_private_key_not_public_only")

        val keyFromPrivate = ECKey(generateRandomPrivate().d)

        Assert.assertFalse(keyFromPrivate.isPubOnly);

        val privateKey = generateRandomPrivate().d
        val ecKey = ECKey(privateKey, FixedPointCombMultiplier().multiply(curve.g, privateKey))

        Assert.assertFalse(ecKey.isPubOnly);
    }

    private fun generateRandomPublic(): ECPublicKeyParameters {
        val keyPair = generateRandomKeyPair()
        return keyPair.public as ECPublicKeyParameters
    }

    private fun generateRandomPrivate(): ECPrivateKeyParameters {
        val keyPair = generateRandomKeyPair()
        return keyPair.private as ECPrivateKeyParameters
    }

    private fun generateRandomKeyPair(): AsymmetricCipherKeyPair {
        val secureRandom = SecureRandom()
        val generator: ECKeyPairGenerator = ECKeyPairGenerator()
        val keygenParams: ECKeyGenerationParameters = ECKeyGenerationParameters(curve, secureRandom)
        generator.init(keygenParams)
        return generator.generateKeyPair()
    }

    // Copied from ECKey to use BouncyCastle key generation
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
    }
}