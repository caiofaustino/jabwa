package dev.caiofaustino.jabwa.createwallet.mvi

import dev.caiofaustino.mvi.MviResult

sealed interface CreateWalletResult : MviResult {
    object ShowWipText : CreateWalletResult
}