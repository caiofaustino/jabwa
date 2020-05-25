package dev.caiofaustino.jabwa.bitcoin

import org.junit.Test
import java.nio.charset.Charset

class Base58CheckTest {

    @Test
    fun test_cat_example() {
        // Encode the byte array of "Cat" with prefix 0x00
        val stringToEncode = "Cat".toByteArray(Charset.defaultCharset())
        val expectedString = "13Z6j3UgCNB"

        val result = Base58Check.encode(byteArrayOf(0), stringToEncode)
        assert(result == expectedString)
    }
}