package dev.caiofaustino.jabwa

import dev.caiofaustino.jabwa.encoding.Base58
import org.junit.Test
import java.math.BigInteger
import java.nio.charset.Charset

class Base58Test {

    @Test
    fun playground() {

    }

    @Test
    fun cat_example_fromString() {
        val base58encodedString = "PdgX"
        val expectedValue = BigInteger("4415860")
        val expectedStringValue = "Cat"

        val result = Base58.fromString(base58encodedString)

        assert(BigInteger(Base58.fromString(base58encodedString)) == expectedValue)
        assert(result.toString(Charset.defaultCharset()) == expectedStringValue)
    }

    @Test
    fun cat_example_fromByteArray() {
        val stringToEncode = "Cat"
        val expectedEncodedValue = "PdgX"
        // 4415860
        assert(Base58.fromByteArray(stringToEncode.toByteArray()) == expectedEncodedValue)
    }
}