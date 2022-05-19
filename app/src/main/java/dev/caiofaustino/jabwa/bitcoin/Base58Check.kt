package dev.caiofaustino.jabwa.bitcoin

import dev.caiofaustino.jabwa.encoding.Base58
import dev.caiofaustino.jabwa.encryption.Sha256

/**
 * Base58Check is an extension of Base58 encoding used in Bitcoin addresses and other payloads.
 *
 * It consists in adding a version prefix to identify the payload and a checksum to verify integrity.
 * Prefix + Payload (n bytes) + checksum (4 bytes)
 */
object Base58Check {
    private const val CHECKSUM_SIZE = 4
    fun encode(prefix: ByteArray, payload: ByteArray): String {
        val content = prefix + payload
        val doubleHash = Sha256.hash(Sha256.hash(content))
        val result = content + doubleHash.copyOfRange(0, CHECKSUM_SIZE)
        return Base58.fromByteArray(result)
    }
}
