package com.example.praktikum7_jasonmanuel_245150201111050.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.praktikum7_jasonmanuel_245150201111050.ui.home.HomeUiState

@Composable
fun HomeScreen(
    uiState: HomeUiState,
    onSearchQueryChange: (String) -> Unit,
    onSearch: () -> Unit,
    onOpenDetail: (Int) -> Unit,
    onOpenProfile: () -> Unit,
    onOpenAbout: () -> Unit,
    onRetry: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        Text(text = "Home", style = MaterialTheme.typography.headlineMedium)
        Text(text = "Cari profil mahasiswa:")

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            OutlinedTextField(
                value = uiState.searchQuery,
                onValueChange = onSearchQueryChange,
                modifier = Modifier.weight(1f),
                label = { Text("Nama pengguna") },
                placeholder = { Text("Masukkan nama...") },
                singleLine = true
            )

            Button(onClick = onSearch, enabled = !uiState.isLoading) {
                Text("Cari")
            }
        }

        Text(
            text = if (uiState.searchQuery.isBlank()) {
                "Pilih mahasiswa untuk melihat detail:"
            } else {
                "Hasil pencarian:"
            },
            style = MaterialTheme.typography.bodyMedium
        )

        when {
            uiState.isLoading -> {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    CircularProgressIndicator()

                    Text(
                        text = "Memuat data mahasiswa..."
                    )
                }
            }

            uiState.errorMessage != null -> {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {

                    Text(
                        text = uiState.errorMessage
                    )

                    Button(
                        onClick = onRetry
                    ) {
                        Text("Coba Lagi")
                    }
                }
            }

            uiState.students.isEmpty() -> {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = if (uiState.searchQuery.isNotBlank()) {
                            "Tidak ada mahasiswa yang ditemukan."
                        } else {
                            "Belum ada data mahasiswa."
                        }
                    )
                }
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
                                horizontalArrangement = Arrangement.spacedBy(12.dp)
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
