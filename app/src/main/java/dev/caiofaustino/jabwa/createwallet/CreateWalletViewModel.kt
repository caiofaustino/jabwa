package dev.caiofaustino.jabwa.createwallet

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import dev.caiofaustino.jabwa.createwallet.mvi.CreateWalletProcessor
import dev.caiofaustino.jabwa.createwallet.mvi.CreateWalletReducer
import dev.caiofaustino.jabwa.createwallet.mvi.CreateWalletUiState
import dev.caiofaustino.mvi.MviStore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.scan
import kotlinx.coroutines.flow.stateIn

private const val UI_STATE = "CREATE_WALLET_UI_STATE"

class CreateWalletViewModel(
    private val processor: CreateWalletProcessor,
    reducer: CreateWalletReducer,
    private val savedState: SavedStateHandle,
    viewModelScope: CoroutineScope = CoroutineScope(Dispatchers.Main + SupervisorJob()),
) : ViewModel(viewModelScope), MviStore<CreateWalletUiState> {

    override val uiState: StateFlow<CreateWalletUiState> =
        processor.resultFlow
            .scan(CreateWalletUiState(), reducer::reduce)
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.Lazily,
                initialValue = savedState[UI_STATE] ?: CreateWalletUiState(),
            )

    override fun onCleared() {
        super.onCleared()
        savedState[UI_STATE] = uiState.value
    }

    companion object {
        val factory =
            viewModelFactory {
                initializer {
                    CreateWalletViewModel(
                        processor = CreateWalletProcessor(),
                        reducer = CreateWalletReducer(),
                        savedState = createSavedStateHandle(),
                    )
                }
            }
    }
}

