package com.example.praktikum7_jasonmanuel_245150201111050.data.remote.dto

import com.example.praktikum7_jasonmanuel_245150201111050.data.model.Student

data class UserDto(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val email: String,
    val university: String
)

fun UserDto.toStudent() = Student(
    id = id,
    name = "$firstName $lastName",
    email = email,
    university = university
)
