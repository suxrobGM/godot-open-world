package com.suxrobgm.openworld.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.suxrobgm.openworld.ui.components.SettingItem
import com.suxrobgm.openworld.ui.theme.GameMenuTheme

@Composable
fun SettingsScreen(onBack: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp)
    ) {
        IconButton(onClick = onBack) {
            Text("← Back", color = Color.White, fontSize = 18.sp)
        }

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "SETTINGS",
            style = MaterialTheme.typography.headlineLarge,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Example settings
        SettingItem(
            title = "Sound Volume",
            description = "Adjust game sound effects"
        )

        SettingItem(
            title = "Music Volume",
            description = "Adjust background music"
        )

        SettingItem(
            title = "Graphics Quality",
            description = "Change rendering quality"
        )

        SettingItem(
            title = "Controls",
            description = "Configure input controls"
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SettingsScreenPreview() {
    GameMenuTheme {
        SettingsScreen(onBack = {})
    }
}
