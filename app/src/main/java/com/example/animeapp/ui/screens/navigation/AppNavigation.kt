package com.example.animeapp.ui.screens.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.animeapp.ui.screens.DetailScreen
import com.example.animeapp.ui.screens.HomeScreen

@Composable
fun AnimeAppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Routes.HOME) {


        composable(Routes.HOME) {

            HomeScreen(navController = navController, viewModel = hiltViewModel())
        }
        composable(
            route = Routes.DETAIL,
            arguments = listOf(navArgument("animeId") { type = NavType.IntType })
        ) { backStackEntry ->
            val animeId = backStackEntry.arguments?.getInt("animeId") ?: 0
            DetailScreen(animeId = animeId, navController = navController, viewModel = hiltViewModel())
        }
    }
}