package com.suxrobgm.openworld.screens

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun MainScreen(onQuit: () -> Unit = {}) {
    var currentScreen by remember { mutableStateOf(Screen.MENU) }

    Surface {
        when (currentScreen) {
            Screen.MENU -> {
                MainMenuScreen(
                    onStartGame = { currentScreen = Screen.GAME },
                    onQuit = onQuit
                )
            }

            Screen.GAME -> {
                GamePlaceholderScreen(
                    onBackToMenu = { currentScreen = Screen.MENU }
                )
            }
        }
    }
}

private enum class Screen {
    MENU,
    GAME
}

@Preview
@Composable
private fun MainScreenPreview() {
    MainScreen()
}
