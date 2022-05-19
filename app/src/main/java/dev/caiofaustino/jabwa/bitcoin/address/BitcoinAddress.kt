package dev.caiofaustino.jabwa.bitcoin.address

import dev.caiofaustino.jabwa.bitcoin.Base58Check
import dev.caiofaustino.jabwa.encryption.Ripmed160
import dev.caiofaustino.jabwa.encryption.Sha256

object BitcoinAddress {
    const val P2PKH_PREFIX = 0x00.toByte()
    const val P2SH_PREFIX = 0x05.toByte()

    const val TESTNET_P2PKH_PREFIX = 0x6F.toByte()
    const val TESTNET_P2SH_PREFIX = 0xC4.toByte()

    private const val PUBLIC_KEY_ARRAY_SIZE = 65

    /**
     * Generate encoded address from public key [ByteArray].
     * Key expected be be size [PUBLIC_KEY_ARRAY_SIZE]
     */
    fun fromPublicKey(publicKey: ByteArray): String {
        if (publicKey.size != PUBLIC_KEY_ARRAY_SIZE) {
            throw IllegalArgumentException(
                "Wrong public key size: is ${publicKey.size} should be $PUBLIC_KEY_ARRAY_SIZE."
            )
        }
        val hashedKey = BitcoinAddress.hash160(publicKey)
        return Base58Check.encode(byteArrayOf(BitcoinAddress.P2PKH_PREFIX), hashedKey)
    }

    /**
     * Double hashing used in Bitcoin addresses. RIPEMD160(SHA256(input))
     */
    fun hash160(byteArray: ByteArray): ByteArray {
        val sha256 = Sha256.hash(byteArray)
        return Ripmed160.hash(sha256)
    }
}
