package com.example.praktikum8_jasonmanuel_245150201111050

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier

import com.example.praktikum8_jasonmanuel_245150201111050.navigation.NavigationLabApp
import com.example.praktikum8_jasonmanuel_245150201111050.ui.theme.Praktikum8_JasonManuel_245150201111050Theme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Praktikum8_JasonManuel_245150201111050Theme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    NavigationLabApp()
                }
            }
        }
    }
}
