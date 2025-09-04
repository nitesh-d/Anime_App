package com.example.animeapp.data.local

import androidx.room.TypeConverter
import com.example.animeapp.data.remote.dto.Genre
import com.example.animeapp.data.remote.dto.Images
import com.example.animeapp.data.remote.dto.Trailer
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    private val gson = Gson()

    @TypeConverter
    fun fromImages(images: Images?): String? {
        return gson.toJson(images)
    }

    @TypeConverter
    fun toImages(imagesString: String?): Images? {
        if (imagesString == null) return null
        val objectType = object : TypeToken<Images>() {}.type
        return gson.fromJson(imagesString, objectType)
    }

    @TypeConverter
    fun fromTrailer(trailer: Trailer?): String? {
        return gson.toJson(trailer)
    }

    @TypeConverter
    fun toTrailer(trailerString: String?): Trailer? {
        if (trailerString == null) return null
        val objectType = object : TypeToken<Trailer>() {}.type
        return gson.fromJson(trailerString, objectType)
    }

    @TypeConverter
    fun fromGenreList(genres: List<Genre>?): String? {
        return gson.toJson(genres)
    }

    @TypeConverter
    fun toGenreList(genresString: String?): List<Genre>? {
        if (genresString == null) return null
        val objectType = object : TypeToken<List<Genre>>() {}.type
        return gson.fromJson(genresString, objectType)
    }

}