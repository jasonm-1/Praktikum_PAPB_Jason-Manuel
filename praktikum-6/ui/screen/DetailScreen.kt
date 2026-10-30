package com.example.praktikum6_jasonmanuel_245150201111050.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

import com.example.praktikum6_jasonmanuel_245150201111050.ui.detail.DetailUiState

@Composable
fun DetailScreen(
    uiState: DetailUiState,
    onBack: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = "Detail Student",
            style = MaterialTheme.typography.headlineMedium
        )

        when {
            uiState.errorMessage != null -> {
                Text(text = uiState.errorMessage)
            }

            uiState.student != null -> {
                val student = uiState.student

                Text(text = "ID: ${student.id}")
                Text(text = "Nama: ${student.name}")
                Text(text = "Program: ${student.program}")
                Text(text = "Angkatan: ${student.year}")
            }
        }

        Button(onClick = onBack) {
            Text("Kembali")
        }
    }
}
