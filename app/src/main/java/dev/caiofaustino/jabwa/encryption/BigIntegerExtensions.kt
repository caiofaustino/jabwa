package dev.caiofaustino.jabwa.encryption

import java.math.BigInteger

fun BigInteger.toUnsignedByteArray(): ByteArray {
    val byteArray = this.toByteArray()
    // A signed negative begins with a zero, remove extra zero byte
    return if (byteArray[0] == 0.toByte()) {
        byteArray.copyOfRange(1, byteArray.size)
    } else {
        byteArray.clone()
    }
}
