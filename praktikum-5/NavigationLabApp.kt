package com.example.navigationlab

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.navigationlab.ui.screen.DetailScreen
import com.example.navigationlab.ui.screen.HomeScreen
import com.example.navigationlab.ui.screen.ProfileScreen
import com.example.navigationlab.ui.screen.AboutScreen

@Composable
fun NavigationLabApp() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.HOME
    ) {
        composable(
            route = Routes.HOME
        ) {
            HomeScreen(
                onOpenDetail = { id -> navController.navigate(Routes.detail(id)) },
                onOpenProfile = { navController.navigate(Routes.PROFILE) },
                onOpenAbout = { navController.navigate(Routes.ABOUT) }
            )
        }

        composable(
            route = Routes.DETAIL,
            arguments = listOf(
                navArgument("studentId") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val studentId = backStackEntry.arguments?.getInt("studentId") ?: 0

            DetailScreen(
                studentId = studentId,
                onBack = { navController.popBackStack() }
            )
        }

        composable(route = Routes.PROFILE)
        {
            ProfileScreen(
                onBack = { navController.popBackStack() }
            )
        }


        composable(route = Routes.ABOUT) {
            AboutScreen(
                onBack = { navController.popBackStack() }
            )
        }
    }
}
