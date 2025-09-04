package com.example.animeapp.data.remote.retrofit

import com.example.animeapp.data.remote.dto.AnimeDetailResponse
import com.example.animeapp.data.remote.dto.TopAnimeResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface JikanApiService
{
    @GET("top/anime")
    suspend fun getTopAnime(): TopAnimeResponse

    @GET("anime/{id}")
    suspend fun getAnimeDetails(@Path("id") animeId: Int): AnimeDetailResponse

//    companion object {
//        private const val BASE_URL = "https://api.jikan.moe/v4/"
//        fun create(): JikanApiService {
//            val retrofit = Retrofit.Builder()
//                .baseUrl(BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build()
//            return retrofit.create(JikanApiService::class.java)
//        }
//    }
}