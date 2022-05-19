package dev.caiofaustino.jabwa.bitcoin

import dev.caiofaustino.jabwa.bitcoin.address.BitcoinAddress
import dev.caiofaustino.jabwa.bitcoin.address.WalletImportFormat
import dev.caiofaustino.jabwa.encryption.ECKey

class SimpleBitcoinWallet(private val key: ECKey) {

    // TODO: privateAddress is wrong because negative number have an extra zero byte at the start

    val publicAddress: String = BitcoinAddress.fromPublicKey(key.publicKey)
    val privateAddress: String = WalletImportFormat.fromPrivateKey(key.privateKey)

    companion object {
        fun generate(): SimpleBitcoinWallet {
            val ecKey = ECKey.generate()
            return SimpleBitcoinWallet(ecKey)
        }
    }
}
