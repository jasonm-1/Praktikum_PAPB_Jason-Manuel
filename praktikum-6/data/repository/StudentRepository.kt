package com.example.praktikum6_jasonmanuel_245150201111050.data.repository

import com.example.praktikum6_jasonmanuel_245150201111050.data.model.Student
import com.example.praktikum6_jasonmanuel_245150201111050.data.source.StudentLocalDataSource

interface StudentRepository {
    fun getStudents(): List<Student>

    fun getStudentById(id: Int): Student?
}

class DefaultStudentRepository(
    private val dataSource: StudentLocalDataSource
) : StudentRepository {

    override fun getStudents(): List<Student> {
        return dataSource.getStudents()
    }

    override fun getStudentById(id: Int): Student? {
        return dataSource.getStudentById(id)
    }
}
