package com.example.animeapp.ui.screens


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.animeapp.ui.screens.reusableComposables.ErrorMessage
import com.example.animeapp.ui.screens.reusableComposables.LoadingIndicator
import com.example.animeapp.viewmodel.DetailViewModel
import com.example.animeapp.viewmodel.UiState

@OptIn(ExperimentalMaterial3Api::class, ExperimentalGlideComposeApi::class)
@Composable
fun DetailScreen(
    animeId: Int,
    navController: NavController,
    viewModel: DetailViewModel
) {
    viewModel.fetchAnimeDetails(animeId)

    val uiState by viewModel.animeDetailState.collectAsState()
    val state = uiState

    Box(modifier = Modifier.fillMaxSize()) {
        if (state is UiState.Success) {
            GlideImage(
                model = state.data.images.jpg.largeImageUrl,
                contentDescription = "Background",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .blur(radius = 25.dp)
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.6f))
            )
        }
        Scaffold(
            containerColor = Color.Transparent,
            topBar = {
                TopAppBar(
                    title = { Text("Anime Details", color = Color.White) },
                    navigationIcon = {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Back",
                                tint = Color.White
                            )
                        }

                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color.Transparent
                    )

                )
            }
        ) { paddingValues ->
            Box(modifier = Modifier.padding(paddingValues).fillMaxSize()) {
                when (state) {
                    is UiState.Loading -> LoadingIndicator()
                    is UiState.Success -> AnimeDetailsContent(anime = state.data, navController)
                    is UiState.Error -> ErrorMessage(
                        message = state.message,
                        onRetry = { viewModel.fetchAnimeDetails(animeId) })
                }
            }
        }
    }
}