package com.example.animeapp.ui.screens

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.animeapp.data.remote.dto.AnimeData
import com.example.animeapp.data.remote.dto.Genre
import com.example.animeapp.ui.screens.navigation.Routes

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun AnimeDetailsContent(anime: AnimeData, navController: NavController) {
    Box(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                //.height(200.dp)
                .verticalScroll(enabled = true, state = rememberScrollState())
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical = 16.dp, horizontal = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val context = LocalContext.current
                GlideImage(
                    model = anime.images.jpg.largeImageUrl,
                    contentDescription = anime.title,
                    modifier = Modifier
                        .fillMaxWidth(0.6f)
                        .aspectRatio(2 / 3f)
                        .clip(RoundedCornerShape(16.dp)),
                    contentScale = ContentScale.FillBounds
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = anime.titleEnglish.toString(),
                    style = MaterialTheme.typography.headlineLarge,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    color = Color.White,
//                softWrap = false,
//                overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Episodes: ${anime.episodes ?: "N/A"} | ⭐ Rating: ${anime.score ?: "N/A"}/10",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.LightGray
                )
                Spacer(modifier = Modifier.height(16.dp))

                ActionButtonsRow(
                    onShareClick = {
                        shareAnime(context, anime.titleEnglish.toString())
                    },
                    onTrailerClick = {
                        val videoID: String? = anime.trailer?.youtubeId

                        if (!videoID.isNullOrEmpty()){
                          //  Log.e("Anime", "$videoID")
                        navController.navigate(
                            Routes.videoPlayer(
                                videoId = videoID,
                                imageId = anime.images.jpg.largeImageUrl.toString()
                            )

                        )
                        }
                        else{
                            Toast.makeText(context, "Trailer not available for ${anime.title}", Toast.LENGTH_SHORT).show()
                        }

                    }
                )

                Spacer(modifier = Modifier.height(16.dp))

                SynopsisSection(synopsis = anime.synopsis ?: "No synopsis available.")
                Spacer(modifier = Modifier.height(24.dp))

                if (!anime.genres.isNullOrEmpty()) {
                    GenreTags(genres = anime.genres)
                }
            }
        }
    }
}

@Composable
private fun ActionButtonsRow(onShareClick: () -> Unit, onTrailerClick: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterHorizontally)
    ) {


        ActionButton(icon = Icons.Default.PlayArrow, text = "Trailer", onClick = onTrailerClick)
        ActionButton(icon = Icons.Default.Share, text = "Share", onClick = onShareClick)
    }
}

fun shareAnime(context: Context, animeTitle: String) {
    val sendIntent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, "Hey, check out this anime I'm watching: $animeTitle!")
        type = "text/plain"
    }

    val shareIntent = Intent.createChooser(sendIntent, "Share '$animeTitle' using")
    context.startActivity(shareIntent)
}

@Composable
private fun ActionButton(icon: ImageVector, text: String, onClick: () -> Unit) {
    OutlinedButton(
        onClick = onClick,
        shape = RoundedCornerShape(50), // Fully rounded
        colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.White),
        border = BorderStroke(1.dp, Color.White.copy(alpha = 0.7f))
    ) {
        Icon(imageVector = icon, contentDescription = text, modifier = Modifier.size(20.dp))
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = text, fontSize = 12.sp)
    }
}

@Composable
private fun SynopsisSection(synopsis: String) {
    Column(horizontalAlignment = Alignment.Start, modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Synopsis",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
        Spacer(modifier = Modifier.height(8.dp))
        Box(
            modifier = Modifier
                .height(200.dp)
                .verticalScroll(enabled = true, state = rememberScrollState())
        ) {
            Text(
                text = synopsis,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.LightGray,
            )
        }
    }
}

@Composable
fun GenreTags(genres: List<Genre>) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally)
    ) {
        genres.take(3).forEach { genre -> // Take up to 3 genres to avoid overflow
            Card(
                shape = RoundedCornerShape(50),
                colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.15f))
            ) {
                Text(
                    text = genre.name,
                    color = Color.White,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                )
            }
        }
    }
}