import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import movies.composeapp.generated.resources.Res
import movies.composeapp.generated.resources.movies_list_popular_movies
import movies.composeapp.generated.resources.movies_list_top_rated_movies
import movies.composeapp.generated.resources.movies_list_upcoming_movies
import org.example.project.domain.model.MovieSection
import org.example.project.domain.model.MovieSection.SectionType.*
import org.example.project.domain.model.movie1
import org.example.project.ui.components.MoviesSection
import org.example.project.ui.movies.MoviesListViewModel
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun MoviesListRoute(
    viewModel: MoviesListViewModel = koinViewModel()
) {
    val moviesListState by viewModel.moviesListState.collectAsStateWithLifecycle()

    MoviesListScreen(moviesListState = moviesListState)
}

@Composable
@Preview
fun MoviesListScreen(
    moviesListState: MoviesListViewModel.MoviesListState,
) {
    Scaffold { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
        ) {
            when (moviesListState) {
                is MoviesListViewModel.MoviesListState.Success -> {
                    LazyColumn(
                        contentPadding = PaddingValues(vertical = 16.dp),
                        verticalArrangement = Arrangement.spacedBy(32.dp)
                    ) {
                        items(moviesListState.movieSections) { movieSection ->
                            when (movieSection.section) {
                                POPULAR -> {
                                    MoviesSection(
                                        title = stringResource(Res.string.movies_list_popular_movies),
                                        movies = movieSection.movies
                                    )
                                }

                                TOP_RATED -> {
                                    MoviesSection(
                                        title = stringResource(Res.string.movies_list_top_rated_movies),
                                        movies = movieSection.movies
                                    )
                                }

                                UPCOMING -> {
                                    MoviesSection(
                                        title = stringResource(Res.string.movies_list_upcoming_movies),
                                        movies = movieSection.movies
                                    )
                                }
                            }
                        }
                    }
                }

                is MoviesListViewModel.MoviesListState.Error -> {
                    Text(
                        text = moviesListState.message,
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(16.dp),
                        textAlign = TextAlign.Center,
                    )
                }

                MoviesListViewModel.MoviesListState.Loading -> {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .align(Alignment.Center)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MoviesListPreview() {
    MoviesListScreen(
        moviesListState = MoviesListViewModel.MoviesListState.Success(
            movieSections = listOf(
                MovieSection(
                    section = POPULAR,
                    movies = listOf(movie1)
                )
            )
        )
    )
}