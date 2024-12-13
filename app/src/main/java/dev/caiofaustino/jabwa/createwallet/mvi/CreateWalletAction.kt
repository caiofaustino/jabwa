package dev.caiofaustino.jabwa.createwallet.mvi

import dev.caiofaustino.mvi.MviAction

sealed interface CreateWalletAction : MviAction {
    object ButtonClicked : CreateWalletAction
}