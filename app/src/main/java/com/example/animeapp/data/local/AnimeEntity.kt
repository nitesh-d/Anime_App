package com.example.animeapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.animeapp.data.remote.dto.Genre
import com.example.animeapp.data.remote.dto.Images
import com.example.animeapp.data.remote.dto.Trailer

@Entity(tableName = "anime_table")
data class AnimeEntity(
    @PrimaryKey
    val malId: Int,
    val title: String,
    val images: Images,
    val episodes: Int?,
    val score: Double?,
    val synopsis: String?,
    val trailer: Trailer?,
    val genres: List<Genre>?,
    val titleEnglish: String
)