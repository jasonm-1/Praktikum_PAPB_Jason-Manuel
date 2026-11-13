package com.example.praktikum8_jasonmanuel_245150201111050.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.praktikum8_jasonmanuel_245150201111050.data.repository.StudentRepository
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: StudentRepository
) : ViewModel() {

    // State untuk tampilan Home
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    // State khusus untuk teks pencarian
    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    // Menyimpan Job untuk Retry dan Refresh
    private var retryJob: Job? = null

    init {
        observeSearch()
    }

    // Mengubah query ketika pengguna mengetik
    fun onSearchQueryChange(query: String) {
        _searchQuery.value = query
    }

    // Mengamati perubahan query menggunakan Flow
    @OptIn(FlowPreview::class)
    private fun observeSearch() {
        viewModelScope.launch {
            _searchQuery
                .debounce(500)
                .distinctUntilChanged()
                .collectLatest { query ->
                    // Membatalkan request Retry atau Refresh sebelumnya
                    retryJob?.cancel()

                    loadData(query)
                }
        }
    }

    // Mengambil daftar mahasiswa atau melakukan pencarian
    private suspend fun loadData(query: String) {
        _uiState.value = _uiState.value.copy(
            isLoading = true,
            errorMessage = null
        )

        try {
            val data = if (query.isBlank()) {
                repository.getStudents()
            } else {
                repository.searchStudents(query)
            }

            _uiState.value = HomeUiState(
                students = data,
                isLoading = false,
                errorMessage = null
            )

        } catch (e: CancellationException) {
            throw e

        } catch (e: Exception) {
            _uiState.value = _uiState.value.copy(
                isLoading = false,
                errorMessage = e.message ?: "Gagal mengambil data"
            )
        }
    }

    // Mengulangi request ketika terjadi error
    fun retry() {
        retryJob?.cancel()

        val query = _searchQuery.value

        retryJob = viewModelScope.launch {
            loadData(query)
        }
    }

    // Memperbarui data berdasarkan query yang sedang digunakan
    fun refresh() {
        retryJob?.cancel()

        val query = _searchQuery.value.trim()

        retryJob = viewModelScope.launch {
            loadData(query)
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
