package org.example.project.domain.model

data class Movie(
    val id: Int,
    val title: String,
    val overview: String,
    val posterUrl: String
)

// fake objects
val movie1 = Movie(
    id = 1,
    title = "A Minecraft Movie",
    overview = "Moview overview",
    posterUrl = "url",
)
