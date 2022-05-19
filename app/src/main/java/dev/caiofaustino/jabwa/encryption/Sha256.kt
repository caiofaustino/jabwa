package dev.caiofaustino.jabwa.encryption

import java.security.MessageDigest

object Sha256 {
    private const val SHA_256 = "SHA-256"
    private val digest = MessageDigest.getInstance(SHA_256)

    fun hash(byteArray: ByteArray): ByteArray {
        digest.reset()
        digest.update(byteArray)
        return digest.digest()
    }
}
