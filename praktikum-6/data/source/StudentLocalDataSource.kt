package com.example.praktikum6_jasonmanuel_245150201111050.data.source

import com.example.praktikum6_jasonmanuel_245150201111050.data.model.Student

class StudentLocalDataSource {

    private val students = listOf(
        Student(202601012, "Alya Putri", "Teknik Informatika", 2026),
        Student(202601019, "Bima Pratama", "Teknik Informatika", 2026),
        Student(202611027, "Citra Lestari", "Teknik Informatika", 2026),
        Student(202411050, "Jason Manuel", "Teknik Informatika", 2024),

        // Dua data mahasiswa tambahan
        Student(202371035, "Indah Lenita", "Sistem Informasi", 2023),
        Student(202571038, "Doni Mario", "Teknik Komputer", 2025)
    )

    fun getStudents(): List<Student> {
        return students
    }

    fun getStudentById(id: Int): Student? {
        return students.find { it.id == id }
    }
}
