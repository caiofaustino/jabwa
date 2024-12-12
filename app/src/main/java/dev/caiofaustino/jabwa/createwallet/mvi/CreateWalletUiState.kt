package dev.caiofaustino.jabwa.createwallet.mvi

import android.os.Parcelable
import dev.caiofaustino.mvi.UiState
import kotlinx.parcelize.Parcelize

@Parcelize
data class CreateWalletUiState(
    val test: String = "",
) : UiState, Parcelable
