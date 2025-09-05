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
    fun getTopAnime(): Flow<List<AnimeData>> {
        return animeDao.getTopAnime().map { entityList ->
            entityList.map { it.toData() }
        }
    }
    suspend fun refreshTopAnime() {
        try {
            val response = apiService.getTopAnime()
            animeDao.insertAllAnime(response.data.map { it.toEntity() })
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun getAnimeDetails(id: Int): Flow<AnimeData?> {
        return animeDao.getAnimeDetails(id).map { it?.toData() }
    }
    suspend fun refreshAnimeDetails(id: Int) {
        try {
            val response = apiService.getAnimeDetails(id)
            animeDao.insertAnimeDetail(response.data.toEntity())
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}