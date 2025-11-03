package com.suxrobgm.openworld.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.suxrobgm.openworld.ui.theme.GameMenuTheme

@Composable
fun CreditSection(role: String, names: List<String>) {
    Column(modifier = Modifier.padding(vertical = 16.dp)) {
        Text(
            text = role.uppercase(),
            style = MaterialTheme.typography.titleSmall,
            color = Color(0xFF00D9FF),
            fontWeight = FontWeight.Bold,
            letterSpacing = 2.sp
        )

        Spacer(modifier = Modifier.height(8.dp))

        names.forEach { name ->
            Text(
                text = name,
                style = MaterialTheme.typography.bodyLarge,
                color = Color.White,
                modifier = Modifier.padding(vertical = 4.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CreditSectionPreview() {
    GameMenuTheme {
        Column(modifier = Modifier.padding(16.dp)) {
            CreditSection(
                role = "Development",
                names = listOf("Sukhrob Ilyosbekov")
            )
            CreditSection(
                role = "Engine",
                names = listOf("Godot Engine 4.5", "Godot-JVM")
            )
        }
    }
}
