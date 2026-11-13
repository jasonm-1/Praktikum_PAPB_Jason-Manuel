package com.example.praktikum8_jasonmanuel_245150201111050.data.repository
import com.example.praktikum8_jasonmanuel_245150201111050.data.model.Student

interface StudentRepository {
    suspend fun getStudents(): List<Student>
    suspend fun getStudent(id: Int): Student
    suspend fun searchStudents(query: String): List<Student>
}
