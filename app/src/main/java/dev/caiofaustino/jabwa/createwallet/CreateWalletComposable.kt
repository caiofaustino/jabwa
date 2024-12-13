package dev.caiofaustino.jabwa.createwallet

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.caiofaustino.jabwa.createwallet.mvi.CreateWalletAction
import dev.caiofaustino.jabwa.createwallet.mvi.CreateWalletUiState
import dev.caiofaustino.jabwa.ui.preview.PreviewThemes
import dev.caiofaustino.jabwa.ui.theme.JabwaTheme

@Composable
fun CreateWalletScreen(
    viewModel: CreateWalletViewModel,
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    CreateWallet(state) { action ->
        viewModel.onUserAction(action)
    }
}

@Composable
fun CreateWallet(
    state: CreateWalletUiState,
    onUserAction: (action: CreateWalletAction) -> Unit,
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
    ) { innerPadding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier =
            Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(25.dp),
        ) {

            TextField(
                value = state.address,
                readOnly = true,
                supportingText = {
                    Text("Address")
                },
                onValueChange = { /* Read Only */},
            )
            Button(
                onClick = {
                    onUserAction(CreateWalletAction.ButtonClicked)
                }
            ) {
                // TODO get the string from @string/generate_address_btn
                Text("Generate Address")
            }
        }
    }
}


@PreviewThemes
@Composable
private fun PreviewThemes() {
    JabwaTheme {
        Surface {
            CreateWallet(CreateWalletUiState()) {}
        }
    }
}