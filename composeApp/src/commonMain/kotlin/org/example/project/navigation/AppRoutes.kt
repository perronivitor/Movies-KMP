package org.example.project.navigation

sealed interface AppRoutes {
    data object MoviesList : AppRoutes
    data class MovieDetail(val id: Int) : AppRoutes
}
