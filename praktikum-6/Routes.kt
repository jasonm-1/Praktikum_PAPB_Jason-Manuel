package com.example.praktikum6_jasonmanuel_245150201111050

object Routes {
    const val HOME = "home"
    const val PROFILE = "profile"
    const val ABOUT = "about"
    const val DETAIL = "detail/{studentId}"

    fun detail(studentId: Int): String {
        return "detail/$studentId"
    }
}
