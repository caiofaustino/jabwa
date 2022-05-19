package dev.caiofaustino.jabwa.encryption

import org.bouncycastle.util.encoders.Hex
import org.junit.Test
import java.math.BigInteger
import java.nio.charset.Charset
import java.util.Locale

class Sha256Test {

    @Test
    fun cat_example_hash() {
        val baseString = "Cat"
        val expectedHashValue = "48735C4FAE42D1501164976AFEC76730B9E5FE467F680BDD8DAFF4BB77674045"

        val catHash = Sha256.hash(baseString.toByteArray(Charset.defaultCharset()))

        val encodedResult = BigInteger(catHash).toString(16)

        println("Sha256: $encodedResult")
        println("Sha256: ${Hex.toHexString(catHash)}")

        assert(encodedResult.uppercase(Locale.getDefault()) == expectedHashValue)
    }
}
