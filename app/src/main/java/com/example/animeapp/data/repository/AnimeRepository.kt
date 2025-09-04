package com.example.animeapp.data.repository

import com.example.animeapp.data.remote.retrofit.JikanApiService

class AnimeRepository(private val apiService: JikanApiService) {
    suspend fun getTopAnime() = apiService.getTopAnime()
    suspend fun getAnimeDetails(id: Int) = apiService.getAnimeDetails(id)
}