package com.example.animeapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animeapp.data.remote.dto.AnimeData
import com.example.animeapp.data.repository.AnimeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val repository: AnimeRepository) : ViewModel() {
    private val _animeDetailState = MutableStateFlow<UiState<AnimeData>>(UiState.Loading)
    val animeDetailState: StateFlow<UiState<AnimeData>> = _animeDetailState.asStateFlow()

    fun fetchAnimeDetails(animeId: Int) {
        viewModelScope.launch {
            repository.getAnimeDetails(animeId).collect { anime ->
                if (anime != null) {
                    _animeDetailState.value = UiState.Success(anime)
                }
            }
        }

        viewModelScope.launch {
            repository.refreshAnimeDetails(animeId)
        }
    }
}