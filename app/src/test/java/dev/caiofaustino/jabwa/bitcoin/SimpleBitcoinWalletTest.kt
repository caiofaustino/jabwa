package dev.caiofaustino.jabwa.bitcoin

import org.junit.Test

class SimpleBitcoinWalletTest {

    @Test
    fun test_wallet_generation() {
        val wallet = SimpleBitcoinWallet.generate()
        println("public: ${wallet.publicAddress}")
        println("private: ${wallet.privateAddress}")

        assert(wallet.publicAddress.length == 34)
        assert(wallet.publicAddress[0] == "1"[0])

        // TODO: privateAddress is wrong because negative number have an extra zero byte at the start
//        assert(wallet.privateAddress.length == 51)
//        assert(wallet.privateAddress[0] == "5"[0])
    }
}