package com.example.praktikum8_jasonmanuel_245150201111050.data.remote

import com.example.praktikum8_jasonmanuel_245150201111050.data.remote.dto.UserDto

class StudentRemoteDataSource(
    private val api: StudentApiService
) {

    suspend fun getStudents(): List<UserDto> {
        return api.getUsers(limit = 10).users
    }

    suspend fun getStudent(id: Int): UserDto {
        return api.getUser(id)
    }

    suspend fun searchStudents(query: String): List<UserDto> {
        return api.searchUsers(query).users
    }
}
