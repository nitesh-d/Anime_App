package com.example.animeapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animeapp.data.remote.dto.AnimeData
import com.example.animeapp.data.repository.AnimeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class DetailViewModel(private val repository: AnimeRepository) : ViewModel() {
    private val _animeDetailState = MutableStateFlow<UiState<AnimeData>>(UiState.Loading)
    val animeDetailState: StateFlow<UiState<AnimeData>> = _animeDetailState.asStateFlow()

    fun fetchAnimeDetails(animeId: Int) {
        viewModelScope.launch {
            _animeDetailState.value = UiState.Loading
            try {
                val response = repository.getAnimeDetails(animeId)
                _animeDetailState.value = UiState.Success(response.data)
            } catch (e: Exception) {
                _animeDetailState.value = UiState.Error(e.message ?: "An unknown error occurred")
            }
        }
    }
}