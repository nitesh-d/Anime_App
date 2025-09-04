package com.example.animeapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animeapp.data.remote.dto.AnimeData
import com.example.animeapp.data.repository.AnimeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: AnimeRepository) : ViewModel() {
    private val _animeListState = MutableStateFlow<UiState<List<AnimeData>>>(UiState.Loading)
    val animeListState: StateFlow<UiState<List<AnimeData>>> = _animeListState.asStateFlow()

    init {
        fetchTopAnime()
    }

    fun fetchTopAnime() {
        viewModelScope.launch {
            _animeListState.value = UiState.Loading
            try {
                val response = repository.getTopAnime()
                _animeListState.value = UiState.Success(response.data)
            } catch (e: Exception) {
                _animeListState.value = UiState.Error(e.message ?: "An unknown error occurred")
            }
        }
    }
}
