package dev.caiofaustino.jabwa.bitcoin.address

import dev.caiofaustino.jabwa.bitcoin.Base58Check

object WalletImportFormat {
    const val PRIVATE_KEY_WIF_PREFIX = 0x80.toByte()
    const val TESTNET_PRIVATE_KEY_WIF_PREFIX = 0xEF.toByte()

    private const val PRIVATE_KEY_ARRAY_SIZE = 32

    /**
     * Generate encoded WIF address from private key [ByteArray].
     * Key expected be be size [PRIVATE_KEY_ARRAY_SIZE]
     */
    fun fromPrivateKey(privateKey: ByteArray) : String {
        if (privateKey.size != PRIVATE_KEY_ARRAY_SIZE) {
            throw IllegalArgumentException(
                "Wrong public key size: is ${privateKey.size} should be $PRIVATE_KEY_ARRAY_SIZE."
            )
        }
        return Base58Check.encode(byteArrayOf(PRIVATE_KEY_WIF_PREFIX), privateKey)
    }
}
