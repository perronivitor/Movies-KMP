package org.example.project.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import org.example.project.domain.model.Movie
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun MoviePoster(
    movie: Movie,
    modifier: Modifier = Modifier,
    onMoviePosterClick: (movieId: Int) -> Unit,
) {
    Column(
        modifier = modifier
            .clickable { onMoviePosterClick(movie.id) }
            .width(140.dp),
    ) {
        Card(
            modifier = modifier
                .height(220.dp),
            shape = RoundedCornerShape(12.dp),
        ) {
            AsyncImage(
                model = movie.posterUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize(),
            )
        }

        Text(
            text = movie.title,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.titleMedium
        )
    }
}

@Preview
@Composable
fun MoviePosterPreview() {
    MaterialTheme {
        MoviePoster(
            onMoviePosterClick = {},
            movie = Movie(
                id = 1,
                title = "Movie Title",
                overview = "This is a brief overview of the movie. It provides a summary of the plot and key elements.",
                posterUrl = "https://via.placeholder.com/300x450.png?text=Movie+Poster",
                genres = null,
                year = 2023,
                duration = "2h 15min",
                rating = "8.2",
                castMembers = null,
            ),
        )
    }
}
