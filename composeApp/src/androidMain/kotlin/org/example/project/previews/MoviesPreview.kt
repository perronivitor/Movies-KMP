package org.example.project.previews

import MoviesListScreen
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import org.example.project.ui.components.MoviePoster

@Preview(showBackground = true)
@Composable
private fun MoviePosterPreview() {
    MoviePoster()
}

@Preview(showBackground = true)
@Composable
private fun MoviesListPreview() {
    MoviesListScreen()
}