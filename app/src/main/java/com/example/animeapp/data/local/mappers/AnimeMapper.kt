package com.example.animeapp.data.mappers

import com.example.animeapp.data.local.AnimeEntity
import com.example.animeapp.data.remote.dto.AnimeData

fun AnimeData.toEntity(): AnimeEntity {
    return AnimeEntity(
        malId = this.malId,
        title = this.title ?: "Untitled",
        images = this.images,
        episodes = this.episodes,
        score = this.score,
        synopsis = this.synopsis,
        trailer = this.trailer,
        genres = this.genres,
        titleEnglish = this.titleEnglish?: title ?: "Unknown"

    )
}

fun AnimeEntity.toData(): AnimeData {
    return AnimeData(
        malId = this.malId,
        title = this.title,
        images = this.images,
        episodes = this.episodes,
        score = this.score,
        synopsis = this.synopsis,
        trailer = this.trailer,
        genres = this.genres,
        titleEnglish = this.titleEnglish
    )
}