
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.example.project.data.network.IMAGE_SMALL_BASE_URL
import org.example.project.data.network.KtorApiClient
import org.example.project.domain.model.Movie
import org.example.project.ui.components.MoviesSection
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun MoviesListRoute() {

    var popularMovies by remember {
        mutableStateOf(emptyList<Movie>())
    }

    LaunchedEffect(Unit) {
        val response = KtorApiClient.getMovies("popular")
        popularMovies = response.results.map {
            Movie(
                id = it.id,
                title = it.title,
                overview = it.overview,
                posterUrl = "$IMAGE_SMALL_BASE_URL${it.posterPath}",
            )
        }
    }

    MoviesListScreen(
        popularMovies = popularMovies,
    )
}

@Composable
@Preview
fun MoviesListScreen(
    popularMovies: List<Movie>
) {
    Scaffold { padding ->
        LazyColumn(
            modifier = Modifier
                .padding(padding),
            contentPadding = PaddingValues(vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(32.dp)
        ) {
            item {
                MoviesSection(
                    title = "Popular Movies",
                    movies = popularMovies
                )
            }

            item {
                MoviesSection(
                    title = "Top Rated Movies",
                    movies = popularMovies
                )
            }

            item {
                MoviesSection(
                    title = "Upcoming Movies",
                    movies = popularMovies
                )
            }
        }
    }
}