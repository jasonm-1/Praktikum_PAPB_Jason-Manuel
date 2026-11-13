package com.example.praktikum7_jasonmanuel_245150201111050.data.remote.dto

data class UserResponse(
    val users: List<UserDto>,
    val total: Int,
    val skip: Int,
    val limit: Int
)
