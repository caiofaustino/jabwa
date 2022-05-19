package dev.caiofaustino.jabwa.bitcoin

import dev.caiofaustino.jabwa.encryption.ECKey
import org.junit.Test

class SimpleBitcoinWalletTest {

    @Test
    fun play() {

//        val bigInt = BigInteger("103649842199441556598144868365423343458701317043750737792021859763176291533996")
//        val byteArray = bigInt.toUnsignedByteArray()
        // TODO this key is giving shorter public address, is this OK?
        val byteArray = byteArrayOf(
            -108, -49, -7, -126, 108, -64, 2, 67, -65, 112, 98, 30, -46, 11, -120, 102, 10, 63, -100, 26, -73, 3, -89,
            32, -2, 13, -78, -27, 126, -87, 68, -11
        )

        val ecKey = ECKey(byteArray)
        val wallet = SimpleBitcoinWallet(ecKey)

        println("public: ${wallet.publicAddress}")
        println("private: ${wallet.privateAddress}")

//        assert(wallet.publicAddress.length == 34)
//        assert(wallet.publicAddress[0] == "1"[0])
//
//        assert(wallet.privateAddress.length == 51)
//        assert(wallet.privateAddress[0] == "5"[0])
    }

    @Test
    fun test_wallet_generation() {
        val wallet = SimpleBitcoinWallet.generate()
        println("public: ${wallet.publicAddress}")
        println("private: ${wallet.privateAddress}")

        // TODO: publicAddress sometimes has 33 length, check if this is OK or not
        assert(wallet.publicAddress.length == 34)
        assert(wallet.publicAddress[0] == "1"[0])

        assert(wallet.privateAddress.length == 51)
        assert(wallet.privateAddress[0] == "5"[0])
    }
}
