package dev.caiofaustino.jabwa.encoding

import org.junit.Test
import java.math.BigInteger
import java.nio.charset.Charset

class Base58Test {

    @Test
    fun test_cat_example_fromString() {
        val base58encodedString = "PdgX"
        val expectedValue = BigInteger("4415860")
        val expectedStringValue = "Cat"

        val result = Base58.fromString(base58encodedString)

        assert(BigInteger(Base58.fromString(base58encodedString)) == expectedValue)
        assert(result.toString(Charset.defaultCharset()) == expectedStringValue)
    }

    @Test
    fun test_cat_example_fromByteArray() {
        val stringToEncode = "Cat"
        val expectedEncodedValue = "PdgX"
        // 4415860
        assert(Base58.fromByteArray(stringToEncode.toByteArray()) == expectedEncodedValue)
    }

    @Test
    fun test_leading_zeros() {
        val byteArrayToEncode = byteArrayOf(0, 0, 1, 2)
        val expectedEncodedValue = "115T"
        // 258
        assert(Base58.fromByteArray(byteArrayToEncode) == expectedEncodedValue)
    }

    @Test
    fun test_negative_byteArray() {
        val byteArrayToEncode = byteArrayOf(-1, -1)
        val expectedEncodedValue = "LUv"
        // [-1, -1] should become [255, 255] = 65535
        assert(Base58.fromByteArray(byteArrayToEncode) == expectedEncodedValue)
    }
}
