package com.example.animeapp.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.animeapp.ui.screens.reusableComposables.ErrorMessage
import com.example.animeapp.ui.screens.reusableComposables.LoadingIndicator
import com.example.animeapp.viewmodel.HomeViewModel
import com.example.animeapp.viewmodel.UiState
import com.example.animeapp.viewmodel.ViewModelFactory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = viewModel(factory = ViewModelFactory)
) {
    val uiState by viewModel.animeListState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Top Anime Series") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    titleContentColor = MaterialTheme.colorScheme.onSurface
                )
            )
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            when (val state = uiState) {
                is UiState.Loading -> LoadingIndicator()
                is UiState.Success -> AnimeGrid(animeList = state.data, navController = navController)
                is UiState.Error -> ErrorMessage(message = state.message, onRetry = { viewModel.fetchTopAnime() })
            }
        }
    }
}