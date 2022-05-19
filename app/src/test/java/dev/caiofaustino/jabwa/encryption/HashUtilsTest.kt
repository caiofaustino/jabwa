package dev.caiofaustino.jabwa.encryption

import org.bouncycastle.util.encoders.Hex
import org.junit.Test
import java.nio.charset.Charset

class HashUtilsTest {

    @Test
    fun cat_example_hash() {
        val baseString = "Cat"
        // TODO check expected result value

        val catDoubleHash = HashUtils.hash160(baseString.toByteArray(Charset.defaultCharset()))
        val encodedResult = Hex.toHexString(catDoubleHash)

        println(encodedResult)

//        assert(encodedResult.toUpperCase() == expectedHashValue)
    }
}
