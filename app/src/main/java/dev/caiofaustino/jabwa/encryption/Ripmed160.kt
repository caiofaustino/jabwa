package dev.caiofaustino.jabwa.encryption

import org.bouncycastle.crypto.digests.RIPEMD160Digest

class Ripmed160 {

    companion object {

        public fun hash(byteArray: ByteArray) : ByteArray {
            val digest = RIPEMD160Digest()
            digest.update(byteArray, 0, byteArray.size)
            val output = ByteArray(20)
            digest.doFinal(output, 0)
            return output
        }
    }
}