package com.example.animeapp.data.remote.retrofit

import com.example.animeapp.data.remote.dto.AnimeDetailResponse
import com.example.animeapp.data.remote.dto.TopAnimeResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface JikanApiService
{
    @GET("top/anime")
    suspend fun getTopAnime(): TopAnimeResponse
    @GET("anime/{id}")
    suspend fun getAnimeDetails(@Path("id") animeId: Int): AnimeDetailResponse
}