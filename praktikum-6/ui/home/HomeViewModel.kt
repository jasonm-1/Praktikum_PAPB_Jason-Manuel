package com.example.praktikum6_jasonmanuel_245150201111050.ui.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.praktikum6_jasonmanuel_245150201111050.data.repository.StudentRepository

class HomeViewModel(
    private val repository: StudentRepository
) : ViewModel() {

    var uiState by mutableStateOf(HomeUiState())
        private set

    fun loadStudents() {
        uiState = HomeUiState(isLoading = true)

        uiState = try {
            HomeUiState(
                students = repository.getStudents()
            )
        } catch (exception: Exception) {
            HomeUiState(
                errorMessage = exception.message
                    ?: "Gagal memuat daftar mahasiswa"
            )
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
