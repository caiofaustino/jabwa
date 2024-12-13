package dev.caiofaustino.jabwa

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import dev.caiofaustino.jabwa.createwallet.CreateWalletScreen
import dev.caiofaustino.jabwa.createwallet.CreateWalletViewModel
import dev.caiofaustino.jabwa.ui.theme.JabwaTheme

@Composable
fun Jabwa() {
    JabwaTheme {
        val createWalletViewModel = viewModel<CreateWalletViewModel>(factory = CreateWalletViewModel.factory)
        CreateWalletScreen(createWalletViewModel)
    }
}