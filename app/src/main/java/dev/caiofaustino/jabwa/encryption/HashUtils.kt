package dev.caiofaustino.jabwa.encryption

object HashUtils {
    /**
     * Double hashing used in addresses. RIPEMD160(SHA256(input))
     */
    fun hash160(byteArray: ByteArray): ByteArray {
        val sha256 = Sha256.hash(byteArray)
        return Ripmed160.hash(sha256)
    }
}
