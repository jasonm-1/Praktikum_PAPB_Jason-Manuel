package com.example.praktikum8_jasonmanuel_245150201111050.data.source

import com.example.praktikum8_jasonmanuel_245150201111050.data.model.Student

class StudentLocalDataSource {

    private val students = listOf(
        Student(202601012, "Alya Putri", "Teknik Informatika", "Universitas Brawijaya"),
        Student(202601019, "Bima Pratama", "Teknik Informatika", "Universitas Brawijaya"),
        Student(202611027, "Citra Lestari", "Teknik Informatika", "Universitas Brawijaya"),
        Student(202411050, "Jason Manuel", "Teknik Informatika", "Universitas Brawijaya"),

        // Dua data mahasiswa tambahan
        Student(202371035, "Indah Lenita", "Sistem Informasi", "Universitas Brawijaya"),
        Student(202571038, "Doni Mario", "Teknik Komputer", "Universitas Brawijaya")
    )

    fun getStudents(): List<Student> {
        return students
    }

    fun getStudentById(id: Int): Student? {
        return students.find { it.id == id }
    }
}
