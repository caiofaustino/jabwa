package dev.caiofaustino.jabwa.encryption

class HashUtils {

    companion object {
        /**
         * Double hashing used in addresses. RIPEMD160(SHA256(input))
         */
        fun doubleHash(byteArray: ByteArray) : ByteArray {
            val sha256 = Sha256.hash(byteArray)
            return Ripmed160.hash(sha256)
        }
    }
}