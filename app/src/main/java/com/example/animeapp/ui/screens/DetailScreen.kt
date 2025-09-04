package com.example.animeapp.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.animeapp.ui.screens.reusableComposables.ErrorMessage
import com.example.animeapp.ui.screens.reusableComposables.LoadingIndicator
import com.example.animeapp.viewmodel.DetailViewModel
import com.example.animeapp.viewmodel.UiState
import com.example.animeapp.viewmodel.ViewModelFactory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    animeId: Int,
    navController: NavController,
    viewModel: DetailViewModel = viewModel(factory = ViewModelFactory)
) {
    // Fetch details when the composable enters the composition
    viewModel.fetchAnimeDetails(animeId)

    val uiState by viewModel.animeDetailState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Details") },
                navigationIcon = {
                    IconButton(onClick = {navController.popBackStack()}) {
                        Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }

                }
            )
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues).fillMaxSize()) {
            when (val state = uiState) {
                is UiState.Loading -> LoadingIndicator()
                is UiState.Success -> AnimeDetailsContent(anime = state.data)
                is UiState.Error -> ErrorMessage(message = state.message, onRetry = { viewModel.fetchAnimeDetails(animeId) })
            }
        }
    }
}