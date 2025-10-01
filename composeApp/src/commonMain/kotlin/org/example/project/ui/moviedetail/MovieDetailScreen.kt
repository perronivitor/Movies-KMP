package org.example.project.ui.moviedetail

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.ArrowLeft
import org.example.project.domain.model.Movie
import org.example.project.domain.model.movie1
import org.example.project.ui.theme.MoviesAppTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun MovieDetailRoute() {
    MovieDetailScreen(movie1)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDetailScreen(movie: Movie) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = "Movie Detail")
                },
                navigationIcon = {
                    Surface(
                        modifier = Modifier
                            .padding(start = 12.dp),
                        shape = MaterialTheme.shapes.small,
                    ) {
                        IconButton(
                            onClick = {

                            },
                            modifier = Modifier
                                .size(32.dp)
                        ) {
                            Icon(
                                imageVector = FontAwesomeIcons.Solid.ArrowLeft,
                                contentDescription = null,
                                modifier = Modifier
                                    .padding(8.dp)
                            )
                        }
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,
                )
            )
        }
    ) { paddingValues ->

    }
}

@Preview(showBackground = true)
@Composable
fun MoviesDetailScreenPreview() {
    MoviesAppTheme {
        MovieDetailScreen(
            movie = movie1,
        )
    }
}