package com.example.animeapp.di

import com.example.animeapp.data.remote.retrofit.JikanApiService
import com.example.animeapp.data.repository.AnimeRepository
import com.example.animeapp.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideJikanApiService(): JikanApiService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(JikanApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideAnimeRepository(apiService: JikanApiService): AnimeRepository {
        return AnimeRepository(apiService)
    }
}