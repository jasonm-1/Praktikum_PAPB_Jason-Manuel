package com.example.praktikum7_jasonmanuel_245150201111050.data.repository
import com.example.praktikum7_jasonmanuel_245150201111050.data.model.Student

interface StudentRepository {
    fun getStudents(callback: (Result<List<Student>>) -> Unit)
    fun getStudent(id: Int, callback: (Result<Student>) -> Unit)
    fun searchStudents(query: String, callback: (Result<List<Student>>) -> Unit)
}
