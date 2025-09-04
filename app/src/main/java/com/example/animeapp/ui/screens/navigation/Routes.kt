package com.example.animeapp.ui.screens.navigation

object Routes {
    const val HOME = "home"
    const val DETAIL = "detail/{animeId}"
    fun detail(animeId: Int) = "detail/$animeId"
}