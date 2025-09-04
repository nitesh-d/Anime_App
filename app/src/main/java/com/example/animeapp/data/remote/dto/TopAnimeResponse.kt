package com.example.animeapp.data.remote.dto

import com.google.gson.annotations.SerializedName

data class TopAnimeResponse(@SerializedName("data") val data: List<AnimeData>)