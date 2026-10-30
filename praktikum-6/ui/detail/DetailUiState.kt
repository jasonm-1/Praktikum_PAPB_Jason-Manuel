package com.example.praktikum6_jasonmanuel_245150201111050.ui.detail

import com.example.praktikum6_jasonmanuel_245150201111050.data.model.Student

data class DetailUiState(
    val student: Student? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)
