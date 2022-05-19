package dev.caiofaustino.jabwa.bitcoin.address

import org.junit.Test
import java.math.BigInteger

class BitcoinAddressTest {

    companion object {
        private const val PUBLIC_KEY_ARRAY_SIZE = 65
    }

    @Test
    fun test_simple_array_address() {
        val initialArray = ByteArray(PUBLIC_KEY_ARRAY_SIZE)
        initialArray[0] = 1.toByte()
        println(BigInteger(initialArray))
        val expectedValue = "13Ekq2kBk86uUKs1vmqoCXvY9Qc1xBGsoE"
        val formatted = BitcoinAddress.fromPublicKey(initialArray)
        assert(formatted == expectedValue)
    }

    @Test
    fun test_simple_array_hash() {
        val initialArray = ByteArray(PUBLIC_KEY_ARRAY_SIZE)
        initialArray[0] = 1.toByte()
        val expectedValue = BigInteger("140098743464449328902310916880294707894965063742").toByteArray()
        // [24, -118, 62, -77, -65, -40, -38, 18, 116, -55, 53, -108, 108, -75, 118, 91, 66, 37, 80, 62]
        val hash = BitcoinAddress.hash160(initialArray)
        assert(hash.contentEquals(expectedValue))
    }

    @Test(expected = IllegalArgumentException::class)
    fun test_key_size_exception() {
        val initialArray = ByteArray(66)
        initialArray[0] = 1.toByte()
        BitcoinAddress.fromPublicKey(initialArray)
    }
}
