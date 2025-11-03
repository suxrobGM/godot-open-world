package com.suxrobgm.openworld.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.suxrobgm.openworld.ui.theme.GameMenuTheme

/**
 * Placeholder screen showing where Godot game integration will happen.
 */
@Composable
fun GamePlaceholderScreen(
    onBackToMenu: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Game Integration Needed",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "This is where the Godot game will be integrated.\n\n" +
                        "For Godot-JVM integration:\n" +
                        "1. Create a Godot Android plugin\n" +
                        "2. Use custom export template\n" +
                        "3. Call this menu from Godot",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(32.dp))

            Button(onClick = onBackToMenu) {
                Text("Back to Menu")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GamePlaceholderScreenPreview() {
    GameMenuTheme {
        GamePlaceholderScreen(
            onBackToMenu = {}
        )
    }
}
