package com.example.animeapp.di

import android.content.Context
import androidx.room.Room
import com.example.animeapp.data.local.AnimeDao
import com.example.animeapp.data.local.AnimeDatabase
import com.example.animeapp.data.remote.retrofit.JikanApiService
import com.example.animeapp.data.repository.AnimeRepository
import com.example.animeapp.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
    fun provideAnimeRepository(apiService: JikanApiService, animeDao: AnimeDao): AnimeRepository {
        return AnimeRepository(apiService, animeDao)
    }

    @Provides
    @Singleton
    fun provideAnimeDatabase(@ApplicationContext context: Context): AnimeDatabase {
        return Room.databaseBuilder(
            context,
            AnimeDatabase::class.java,
            "anime_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideAnimeDao(database: AnimeDatabase) = database.animeDao()
}