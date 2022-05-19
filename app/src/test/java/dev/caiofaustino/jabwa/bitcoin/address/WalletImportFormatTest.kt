package dev.caiofaustino.jabwa.bitcoin.address

import org.junit.Test
import java.math.BigInteger

class WalletImportFormatTest {

    companion object {
        private const val PRIVATE_KEY_ARRAY_SIZE = 32
    }

    @Test
    fun test_simple_array() {
        val initialArray = ByteArray(PRIVATE_KEY_ARRAY_SIZE)
        initialArray.fill(1.toByte())
        println(BigInteger(initialArray))
        val expectedValue = "5HpjE2Hs7vjU4SN3YyPQCdhzCu92WoEeuE6PWNuiPyTu3ESGnzn"
        val formatted = WalletImportFormat.fromPrivateKey(initialArray)
        assert(formatted == expectedValue)
    }

    @Test(expected = IllegalArgumentException::class)
    fun test_key_size_exception() {
        val initialArray = ByteArray(33)
        initialArray[0] = 1.toByte()
        WalletImportFormat.fromPrivateKey(initialArray)
    }
}
