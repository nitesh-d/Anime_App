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
import com.example.animeapp.ui.screens.reusableComposables.VideoPlayer
import java.net.URLDecoder
import java.nio.charset.StandardCharsets

@Composable
fun AnimeAppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Routes.HOME) {

        composable(Routes.HOME) {

            HomeScreen(navController = navController, viewModel = hiltViewModel())
        }

        composable(route = Routes.VIDEO_PLAYER,
            arguments = listOf(
                navArgument("videoId") { type = NavType.StringType },
                navArgument("imageId") {type = NavType.StringType}
            )
        ) {backStackEntry ->
            val videoId = backStackEntry.arguments?.getString("videoId")?: return@composable
            val imageId = backStackEntry.arguments?.getString("imageId")?: return@composable
            val decodedImageUrl = URLDecoder.decode(imageId, StandardCharsets.UTF_8.toString())
        VideoPlayer(navController=navController, videoId=videoId, largeImageUrl = decodedImageUrl)
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