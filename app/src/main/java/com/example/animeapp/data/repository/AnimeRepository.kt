package com.example.animeapp.data.repository

import com.example.animeapp.data.local.AnimeDao
import com.example.animeapp.data.mappers.toData
import com.example.animeapp.data.mappers.toEntity
import com.example.animeapp.data.remote.dto.AnimeData
import com.example.animeapp.data.remote.retrofit.JikanApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AnimeRepository @Inject constructor(
    private val apiService: JikanApiService,
    private val animeDao: AnimeDao
) {

    // Get the list of top anime
    fun getTopAnime(): Flow<List<AnimeData>> {
        // First, return data from the database
        return animeDao.getTopAnime().map { entityList ->
            // Convert the list of entities to a list of data models
            entityList.map { it.toData() }
        }
    }

    // This function will be called from the ViewModel to refresh the data
    suspend fun refreshTopAnime() {
        try {
            val response = apiService.getTopAnime()
            // Convert DTOs to Entities and save to the database
            animeDao.insertAllAnime(response.data.map { it.toEntity() })
        } catch (e: Exception) {
            // Handle network errors, e.g., log them.
            // The user will still see the cached data.
            e.printStackTrace()
        }
    }

    // Get the details for a single anime
    fun getAnimeDetails(id: Int): Flow<AnimeData?> {
        return animeDao.getAnimeDetails(id).map { it?.toData() }
    }

    // Refresh the details for a single anime
    suspend fun refreshAnimeDetails(id: Int) {
        try {
            val response = apiService.getAnimeDetails(id)
            animeDao.insertAnimeDetail(response.data.toEntity())
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}