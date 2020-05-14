package dev.caiofaustino.jabwa.encryption

import org.bouncycastle.util.encoders.Hex
import org.junit.Test
import java.nio.charset.Charset

class Ripmed160Test {

    @Test
    fun cat_example_hash() {
        val baseString = "Cat"
        val expectedHashValue = "A69C162C45E42F29B89CB898FE0F816598D72E35"

        val catHash = Ripmed160.hash(baseString.toByteArray(Charset.defaultCharset()))
        val encodedResult = Hex.toHexString(catHash)

        assert(encodedResult.toUpperCase() == expectedHashValue)
    }
}