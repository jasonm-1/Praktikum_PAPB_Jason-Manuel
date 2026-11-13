package com.example.praktikum8_jasonmanuel_245150201111050.data.remote

import com.example.praktikum8_jasonmanuel_245150201111050.data.remote.dto.UserDto
import com.example.praktikum8_jasonmanuel_245150201111050.data.remote.dto.UserResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface StudentApiService {

    @GET("users")
    suspend fun getUsers(
        @Query("limit") limit: Int = 10
    ): UserResponse

    @GET("users/{id}")
    suspend fun getUser(
        @Path("id") id: Int
    ): UserDto

    @GET("users/search")
    suspend fun searchUsers(
        @Query("q") query: String
    ): UserResponse
}
