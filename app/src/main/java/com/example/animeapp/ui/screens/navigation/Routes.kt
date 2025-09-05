package com.example.animeapp.ui.screens.navigation

object Routes {
    const val HOME = "home"
    const val DETAIL = "detail/{animeId}"
    fun detail(animeId: Int) = "detail/$animeId"
    const val VIDEO_PLAYER = "video?videoId={videoId}&imageId={imageId}"
    fun videoPlayer(videoId: String, imageId: String): String {
        val encodedUrl = java.net.URLEncoder.encode(imageId, java.nio.charset.StandardCharsets.UTF_8.toString())
        return "video?videoId=$videoId&imageId=$encodedUrl"
    }
}