package com.ahmet.bostanciklioglu.movleappwithktorclientincompose.ui.movies

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.ahmet.bostanciklioglu.movleappwithktorclientincompose.R
import com.ahmet.bostanciklioglu.movleappwithktorclientincompose.data.models.Movie
import com.ahmet.bostanciklioglu.movleappwithktorclientincompose.ui.theme.MovleAppWithKtorClientInComposeTheme
import io.ktor.http.ContentType.Application.Json

@Composable
fun MovieItem(
    movie: Movie
) {
    val spacing = 8.dp
    Card(
        modifier = Modifier
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        MaterialTheme.colorScheme.surfaceVariant,
                        MaterialTheme.colorScheme.surface
                    ),
                    start = Offset(0f, Float.POSITIVE_INFINITY),
                    end = Offset(Float.POSITIVE_INFINITY, 0f)
                )
            )
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(spacing)
            .clip(RoundedCornerShape(8.dp)),
            elevation = CardDefaults.cardElevation(4.dp)

    ) {
        Row(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current).data(movie.fullPosterPath)
                    .crossfade(true).build(),
                contentDescription = movie.title,
                placeholder = painterResource(id = R.drawable.ic_launcher_foreground),
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .weight(0.4f)
                    .clip(
                        RoundedCornerShape(12.dp)
                    ),
                alignment = Alignment.Center

            )
            Column(
                modifier = Modifier
                    .weight(0.6f)
                    .padding(start = 12.dp)
            ) {
                Text(
                    text = movie.originalTitle,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = Modifier.size(12.dp))

                Text(
                    text = movie.overview,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    maxLines = 7,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.size(12.dp))

                Text(
                    text = "IMDB ${movie.voteAverage}",
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .clip(
                            RoundedCornerShape(8.dp)
                        )
                        .background(Color.Yellow)
                        .padding(all = 4.dp)
                )
            }
        }
    }
}

val movie = Movie(
    adult = false,
    backdropPath = "potenti",
    genreIds = listOf(),
    id = 2580,
    originalLanguage = "adolescens",
    originalTitle = "fugit",
    overview = "felis",
    popularity = 4.5,
    posterPath = "honestatis",
    releaseDate = "malorum",
    title = "ad",
    video = false,
    voteAverage = 6.7,
    voteCount = 9744
)

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun MovieItemPreviewDark() {
    MovleAppWithKtorClientInComposeTheme {
        MovieItem(movie)
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun MovieItemPreviewLight() {
    MovleAppWithKtorClientInComposeTheme {
        MovieItem(movie)
    }
}