package dev.caiofaustino.jabwa.createwallet.mvi

import dev.caiofaustino.mvi.MviProcessor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class CreateWalletProcessor(
    private val coroutineScope: CoroutineScope,
) : MviProcessor<CreateWalletAction, CreateWalletResult> {

    private val _resultFlow = MutableSharedFlow<CreateWalletResult>()
    override val resultFlow: Flow<CreateWalletResult> = _resultFlow.asSharedFlow()

    override fun process(action: CreateWalletAction) {
        coroutineScope.launch {
            when (action) {
                CreateWalletAction.ButtonClicked -> {
                    _resultFlow.emit(CreateWalletResult.ShowWipText)
                }
            }
        }
    }
}