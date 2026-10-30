package com.example.praktikum6_jasonmanuel_245150201111050.ui.home

import com.example.praktikum6_jasonmanuel_245150201111050.data.model.Student

data class HomeUiState(
    val students: List<Student> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)
