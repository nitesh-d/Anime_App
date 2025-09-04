package com.example.animeapp.data.remote.dto

import com.example.animeapp.data.remote.dto.Jpg
import com.google.gson.annotations.SerializedName

data class Images(@SerializedName("jpg") val jpg: Jpg)