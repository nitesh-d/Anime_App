package com.example.animeapp.data.remote.dto

import com.example.animeapp.data.remote.dto.Genre
import com.example.animeapp.data.remote.dto.Images
import com.example.animeapp.data.remote.dto.Trailer
import com.google.gson.annotations.SerializedName

data class AnimeData(
    @SerializedName("mal_id") val malId: Int,
    @SerializedName("title") val title: String,
    @SerializedName("episodes") val episodes: Int?,
    @SerializedName("score") val score: Double?,
    @SerializedName("images") val images: Images,
    @SerializedName("synopsis") val synopsis: String?,
    @SerializedName("trailer") val trailer: Trailer?,
    @SerializedName("genres") val genres: List<Genre>?
)