package com.example.praktikum6_jasonmanuel_245150201111050.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.praktikum6_jasonmanuel_245150201111050.ui.home.HomeUiState

@Composable
fun HomeScreen(
    uiState: HomeUiState,
    onOpenDetail: (Int) -> Unit,
    onOpenProfile: () -> Unit,
    onOpenAbout: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = "Home",
            style = MaterialTheme.typography.headlineMedium
        )

        Text(
            text = "Pilih mahasiswa untuk melihat detail:"
        )

        // Menampilkan kondisi daftar mahasiswa.
        when {
            uiState.isLoading -> {
                Text("Memuat daftar mahasiswa...")
            }

            uiState.errorMessage != null -> {
                Text(text = uiState.errorMessage)
            }

            uiState.students.isEmpty() -> {
                Text("Belum ada data mahasiswa.")
            }

            else -> {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(
                        items = uiState.students,
                        key = { student -> student.id }
                    ) { student ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    onOpenDetail(student.id)
                                }
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                horizontalArrangement =
                                    Arrangement.spacedBy(12.dp)
                            ) {
                                Column {
                                    Text(
                                        text = "ID: ${student.id}",
                                        style = MaterialTheme.typography.bodyMedium
                                    )

                                    Text(
                                        text = student.name,
                                        style = MaterialTheme.typography.titleMedium
                                    )

                                    Text(
                                        text = "${student.program} • ${student.year}",
                                        style = MaterialTheme.typography.bodySmall
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }

        OutlinedButton(
            onClick = onOpenProfile,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Buka Profile")
        }

        Button(
            onClick = onOpenAbout,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Buka About")
        }
    }
}
