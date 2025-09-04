package com.example.animeapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.animeapp.data.remote.dto.AnimeData
import com.example.animeapp.ui.screens.navigation.Routes

@Composable
fun AnimeGrid(animeList: List<AnimeData>, navController: NavController) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 150.dp),
        contentPadding = PaddingValues(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(animeList){ anime ->
            AnimeCard(anime = anime, onClick = {
                navController.navigate(Routes.detail(anime.malId))
            })

        }

    }
}