package com.example.animeapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface AnimeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllAnime(animeList: List<AnimeEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAnimeDetail(anime: AnimeEntity)

    @Query("SELECT * FROM anime_table ORDER BY score DESC")
    fun getTopAnime(): Flow<List<AnimeEntity>>

    @Query("SELECT * FROM anime_table WHERE malId = :animeId")
    fun getAnimeDetails(animeId: Int): Flow<AnimeEntity?>

    @Query("DELETE FROM anime_table")
    suspend fun clearAllAnime()
}