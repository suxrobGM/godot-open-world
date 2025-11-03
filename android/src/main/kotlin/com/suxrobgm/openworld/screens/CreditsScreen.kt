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
import com.suxrobgm.openworld.ui.components.CreditSection
import com.suxrobgm.openworld.ui.theme.GameMenuTheme

@Composable
fun CreditsScreen(onBack: () -> Unit) {
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
            text = "CREDITS",
            style = MaterialTheme.typography.headlineLarge,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(32.dp))

        CreditSection(
            role = "Development",
            names = listOf("Your Name")
        )

        CreditSection(
            role = "Engine",
            names = listOf("Godot Engine 4.5", "Godot-JVM")
        )

        CreditSection(
            role = "UI Framework",
            names = listOf("Jetpack Compose")
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CreditsScreenPreview() {
    GameMenuTheme {
        CreditsScreen(onBack = {})
    }
}
