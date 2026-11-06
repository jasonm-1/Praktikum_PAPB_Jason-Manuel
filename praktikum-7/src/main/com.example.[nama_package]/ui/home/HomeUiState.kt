package com.example.praktikum7_jasonmanuel_245150201111050.ui.home

import com.example.praktikum7_jasonmanuel_245150201111050.data.model.Student

data class HomeUiState(
    val students: List<Student> = emptyList(),
    val searchQuery: String = "",
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)
