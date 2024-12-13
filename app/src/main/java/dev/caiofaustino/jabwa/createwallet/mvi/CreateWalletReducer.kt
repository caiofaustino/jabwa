package dev.caiofaustino.jabwa.createwallet.mvi

import dev.caiofaustino.mvi.StateReducer

class CreateWalletReducer : StateReducer<CreateWalletResult, CreateWalletUiState> {

    override fun reduce(
        previousState: CreateWalletUiState,
        result: CreateWalletResult
    ): CreateWalletUiState =
        when (result) {
            CreateWalletResult.ShowWipText -> {
                previousState.copy(address = "Sorry feature is still WIP")
            }
        }

}