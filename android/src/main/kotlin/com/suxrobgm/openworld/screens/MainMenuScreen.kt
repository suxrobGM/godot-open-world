package com.suxrobgm.openworld.screens

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.animation.with
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.suxrobgm.openworld.ui.components.GameMenuButton
import com.suxrobgm.openworld.ui.theme.GameMenuTheme

/**
 * Enhanced game menu screen with animations and better styling
 */
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainMenuScreen(
    onStartGame: () -> Unit = {},
    onSettings: () -> Unit = {},
    onCredits: () -> Unit = {},
    onQuit: () -> Unit = {}
) {
    var currentScreen by remember { mutableStateOf(MenuScreen.MAIN) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF1A1A2E),
                        Color(0xFF16213E),
                        Color(0xFF0F3460)
                    )
                )
            )
    ) {
        AnimatedContent(
            targetState = currentScreen,
            transitionSpec = {
                (fadeIn() + slideInHorizontally())
                    .togetherWith(fadeOut() + slideOutHorizontally())
            },
            label = "menu_navigation"
        ) { screen ->
            when (screen) {
                MenuScreen.MAIN -> MainMenuContent(
                    onStartGame = onStartGame,
                    onSettings = { currentScreen = MenuScreen.SETTINGS },
                    onCredits = { currentScreen = MenuScreen.CREDITS },
                    onQuit = onQuit
                )

                MenuScreen.SETTINGS -> SettingsScreen(
                    onBack = { currentScreen = MenuScreen.MAIN }
                )

                MenuScreen.CREDITS -> CreditsScreen(
                    onBack = { currentScreen = MenuScreen.MAIN }
                )
            }
        }
    }
}

@Composable
private fun MainMenuContent(
    onStartGame: () -> Unit,
    onSettings: () -> Unit,
    onCredits: () -> Unit,
    onQuit: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Game Title
        Text(
            text = "GODOT",
            style = MaterialTheme.typography.displayLarge.copy(
                fontSize = 64.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 8.sp
            ),
            color = Color(0xFFE94560)
        )

        Text(
            text = "OPEN WORLD",
            style = MaterialTheme.typography.headlineMedium.copy(
                fontSize = 28.sp,
                fontWeight = FontWeight.Light,
                letterSpacing = 12.sp
            ),
            color = Color(0xFF00D9FF)
        )

        Spacer(modifier = Modifier.height(80.dp))

        // Menu Buttons
        GameMenuButton(
            text = "START GAME",
            onClick = onStartGame,
            primary = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        GameMenuButton(
            text = "SETTINGS",
            onClick = onSettings
        )

        Spacer(modifier = Modifier.height(16.dp))

        GameMenuButton(
            text = "CREDITS",
            onClick = onCredits
        )

        Spacer(modifier = Modifier.height(16.dp))

        GameMenuButton(
            text = "QUIT",
            onClick = onQuit
        )

        Spacer(modifier = Modifier.height(40.dp))

        // Version info
        Text(
            text = "Version 0.1.0",
            style = MaterialTheme.typography.bodySmall,
            color = Color.Gray
        )
    }
}

enum class MenuScreen {
    MAIN, SETTINGS, CREDITS
}

@Preview(showBackground = true)
@Composable
fun MainMenuScreenPreview() {
    GameMenuTheme {
        MainMenuScreen(
            onStartGame = {},
            onSettings = {},
            onCredits = {},
            onQuit = {}
        )
    }
}
