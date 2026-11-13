package com.example.praktikum7_jasonmanuel_245150201111050.ui.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.praktikum7_jasonmanuel_245150201111050.data.repository.StudentRepository

class HomeViewModel(
    private val repository: StudentRepository
) : ViewModel() {

    var uiState by mutableStateOf(HomeUiState())
        private set

    init {
        loadStudents()
    }

    fun onSearchQueryChange(query: String) {
        uiState = uiState.copy(
            searchQuery = query
        )
    }

    fun loadStudents() {
        uiState = uiState.copy(
            isLoading = true,
            errorMessage = null
        )

        repository.getStudents { result ->
            result
                .onSuccess { data ->
                    uiState = uiState.copy(
                        students = data,
                        isLoading = false,
                        errorMessage = null
                    )
                }
                .onFailure { error ->
                    uiState = uiState.copy(
                        isLoading = false,
                        errorMessage = error.message
                            ?: "Gagal mengambil data"
                    )
                }
        }
    }

    fun searchStudents() {
        val query = uiState.searchQuery.trim()

        if (query.isEmpty()) {
            loadStudents()
            return
        }

        uiState = uiState.copy(
            isLoading = true,
            errorMessage = null
        )

        repository.searchStudents(query) { result ->
            result
                .onSuccess { data ->
                    uiState = uiState.copy(
                        students = data,
                        isLoading = false,
                        errorMessage = null
                    )
                }
                .onFailure { error ->
                    uiState = uiState.copy(
                        isLoading = false,
                        errorMessage = error.message
                            ?: "Gagal mencari mahasiswa"
                    )
                }
        }
    }

    fun retry() {
        if (uiState.searchQuery.isBlank()) {
            loadStudents()
        } else {
            searchStudents()
        }
    }

    companion object {
        fun factory(
            repository: StudentRepository
        ): ViewModelProvider.Factory =
            object : ViewModelProvider.Factory {

                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(
                    modelClass: Class<T>
                ): T {
                    if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
                        return HomeViewModel(repository) as T
                    }

                    throw IllegalArgumentException(
                        "Unknown ViewModel class: ${modelClass.name}"
                    )
                }
            }
    }
}
