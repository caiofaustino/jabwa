package dev.caiofaustino.jabwa.encryption

import org.bouncycastle.crypto.digests.RIPEMD160Digest

object Ripmed160 {
    private const val initialArraySize = 20
    fun hash(byteArray: ByteArray): ByteArray {
        val digest = RIPEMD160Digest()
        digest.update(byteArray, 0, byteArray.size)
        val output = ByteArray(size = initialArraySize)
        digest.doFinal(output, 0)
        return output
    }
}
