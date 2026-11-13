package com.example.praktikum8_jasonmanuel_245150201111050.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

import com.example.praktikum8_jasonmanuel_245150201111050.Routes
import com.example.praktikum8_jasonmanuel_245150201111050.data.remote.RetrofitClient
import com.example.praktikum8_jasonmanuel_245150201111050.data.remote.StudentRemoteDataSource
import com.example.praktikum8_jasonmanuel_245150201111050.data.repository.DefaultStudentRepository
import com.example.praktikum8_jasonmanuel_245150201111050.ui.detail.DetailViewModel
import com.example.praktikum8_jasonmanuel_245150201111050.ui.home.HomeViewModel
import com.example.praktikum8_jasonmanuel_245150201111050.ui.screen.AboutScreen
import com.example.praktikum8_jasonmanuel_245150201111050.ui.screen.DetailScreen
import com.example.praktikum8_jasonmanuel_245150201111050.ui.screen.HomeScreen
import com.example.praktikum8_jasonmanuel_245150201111050.ui.screen.ProfileScreen

@Composable
fun NavigationLabApp() {

    // Membuat Remote Data Source
    val remoteDataSource = remember {
        StudentRemoteDataSource(RetrofitClient.apiService)
    }

    // Membuat Repository
    val repository = remember {
        DefaultStudentRepository(remoteDataSource)
    }

    // Membuat NavController
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.HOME
    ) {

        // HOME
        composable(route = Routes.HOME) {

            val homeViewModel: HomeViewModel = viewModel(
                factory = HomeViewModel.factory(repository)
            )

            // Mengambil StateFlow dari HomeViewModel
            val homeUiState by homeViewModel.uiState.collectAsStateWithLifecycle()
            val searchQuery by homeViewModel.searchQuery.collectAsStateWithLifecycle()

            HomeScreen(
                uiState = homeUiState,
                searchQuery = searchQuery,
                onSearchQueryChange = homeViewModel::onSearchQueryChange,

                onOpenDetail = { id ->
                    navController.navigate(Routes.detail(id))
                },

                onOpenProfile = {
                    navController.navigate(Routes.PROFILE)
                },

                onOpenAbout = {
                    navController.navigate(Routes.ABOUT)
                },

                onRetry = homeViewModel::retry,

                // Menghubungkan tombol Refresh dengan HomeViewModel
                onRefresh = homeViewModel::refresh
            )
        }

        // DETAIL
        composable(
            route = Routes.DETAIL,
            arguments = listOf(
                navArgument("studentId") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->

            val studentId =
                backStackEntry.arguments?.getInt("studentId") ?: -1

            val detailViewModel: DetailViewModel = viewModel(
                factory = DetailViewModel.factory(repository)
            )

            // Memuat data berdasarkan ID
            LaunchedEffect(studentId) {
                detailViewModel.loadStudent(studentId)
            }

            // Mengambil StateFlow dari DetailViewModel
            val detailUiState by detailViewModel.uiState.collectAsStateWithLifecycle()

            DetailScreen(
                uiState = detailUiState,
                onBack = {
                    navController.popBackStack()
                }
            )
        }

        // PROFILE
        composable(route = Routes.PROFILE) {
            ProfileScreen(
                onBack = {
                    navController.popBackStack()
                }
            )
        }

        // ABOUT
        composable(route = Routes.ABOUT) {
            AboutScreen(
                onBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}
