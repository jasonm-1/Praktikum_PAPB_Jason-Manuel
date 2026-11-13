package com.example.praktikum8_jasonmanuel_245150201111050.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

import com.example.praktikum8_jasonmanuel_245150201111050.ui.detail.DetailUiState

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
            uiState.isLoading -> {
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    CircularProgressIndicator()

                    Text("Memuat detail mahasiswa...")
                }
            }

            uiState.errorMessage != null -> {
                Text(text = uiState.errorMessage)
            }

            uiState.student != null -> {
                val student = uiState.student

                Text(
                    text = student.name,
                    style = MaterialTheme.typography.headlineSmall
                )

                Text("ID: ${student.id}")
                Text("Email: ${student.email}")
                Text("University: ${student.university}")
            }

            else -> {
                Text("Data mahasiswa belum tersedia.")
            }
        }

        Button(onClick = onBack) {
            Text("Kembali")
        }
    }
}
