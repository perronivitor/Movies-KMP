package org.example.project.data.network

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.auth.*
import io.ktor.client.plugins.auth.providers.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import org.example.project.data.network.model.CreditsListResponse
import org.example.project.data.network.model.MovieResponse
import org.example.project.data.network.model.MoviesListResponse

private const val BASE_URL = "https://api.themoviedb.org"
const val IMAGE_SMALL_BASE_URL = "https://image.tmdb.org/t/p/w154"

private const val TOKEN =
    "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJmYmNkZjNjZTNmYjIzMDA0ZWE5YWY4ZDlhOTFiYjkzZiIsIm5iZiI6MTYyMjQ2NDc3NS41NjQsInN1YiI6IjYwYjRkOTA3Yzc0MGQ5MDA0MjRiYzJjMSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.DgqZVROyLGD_qgIjZPuRFl6xrxirz-mzLFVmcWOVbn0"

class KtorApiClient {

    private val client = HttpClient {
        expectSuccess = true
        install(ContentNegotiation) {
            json(
                Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                }
            )
        }

        install(Auth) {
            bearer {
                loadTokens {
                    BearerTokens(
                        accessToken = TOKEN,
                        ""
                    )
                }
            }
        }

        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.ALL
            sanitizeHeader { header -> header == HttpHeaders.Authorization }
        }
    }

    suspend fun getMovies(category: String): MoviesListResponse {
        return client.get("$BASE_URL/3/movie/$category") {
            this.addLanguageParam()
        }.body()
    }

    suspend fun getMovieDetail(id: Int): MovieResponse {
        return client.get("$BASE_URL/3/movie/$id") {
            this.addLanguageParam()
        }.body()
    }

    suspend fun getCredits(movieId: Int): CreditsListResponse {
        return client.get("$BASE_URL/3/movie/$movieId/credits") {
            this.addLanguageParam()
        }.body()
    }

    private fun HttpRequestBuilder.addLanguageParam(language: String = "pt-BR") {
        parameter("language", language)
    }
}
