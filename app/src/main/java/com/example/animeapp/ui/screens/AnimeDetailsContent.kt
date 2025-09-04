package com.example.animeapp.ui.screens

import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.animeapp.data.remote.dto.AnimeData


@Composable
fun AnimeDetailsContent(anime: AnimeData) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Here you would implement the video player.
        // For this example, we just show the poster image.
        AsyncImage(
            model = anime.images.jpg.largeImageUrl,
            contentDescription = anime.title,
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .clip(RoundedCornerShape(12.dp)),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(16.dp))
        Text(anime.title, style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))
        Text("⭐ Score: ${anime.score ?: "N/A"}  |  Episodes: ${anime.episodes ?: "N/A"}")
        Spacer(modifier = Modifier.height(8.dp))
        Text("Genres: ${anime.genres?.joinToString { it.name } ?: "Not available"}", style = MaterialTheme.typography.bodyMedium)
        Spacer(modifier = Modifier.height(16.dp))

            Text("Synopsis", style = MaterialTheme.typography.titleLarge)
        Box(modifier = Modifier.verticalScroll(state = rememberScrollState(), enabled = true)) { Text(
                anime.synopsis ?: "No synopsis available.",
                style = MaterialTheme.typography.bodyLarge
            )
        }
        }

}