package com.suxrobgm.openworld

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.suxrobgm.openworld.screens.MainScreen
import com.suxrobgm.openworld.ui.theme.GameMenuTheme

/**
 * Main activity for the game menu.
 * Displays Jetpack Compose UI that can be integrated with Godot.
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            GameMenuTheme {
                MainScreen(
                    onQuit = { finish() }
                )
            }
        }
    }
}
