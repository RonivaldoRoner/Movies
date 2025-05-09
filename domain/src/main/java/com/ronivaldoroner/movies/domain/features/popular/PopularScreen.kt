package com.ronivaldoroner.movies.domain.features.popular


import com.ronivaldoroner.movies.domain.base.state.ScreenState
import com.ronivaldoroner.movies.domain.base.state.State
import com.ronivaldoroner.movies.domain.features.commons.SessionId
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PopularScreen(
    override val state: ScreenState = ScreenState.Loading,
    override val userId: String = "usuario",
    override val featureId: String = "feature",
    val popularPage: PopularPage? = null,
) : State, SessionId

@Serializable
data class PopularPage(
    @SerialName("page")
    val page: Int,
    @SerialName("results")
    val movies: List<Movie>,
    @SerialName("total_pages")
    val totalPages: Int,
    @SerialName("total_results")
    val totalResults: Int
)

@Serializable
data class Movie(
    @SerialName("adult")
    val adult: Boolean,
    @SerialName("backdrop_path")
    val backdropPath: String,
    @SerialName("genre_ids")
    val genreIds: List<Int>,
    @SerialName("id")
    val id: Int,
    @SerialName("original_language")
    val originalLanguage: String,
    @SerialName("original_title")
    val originalTitle: String,
    @SerialName("overview")
    val overview: String,
    @SerialName("popularity")
    val popularity: Double,
    @SerialName("poster_path")
    val posterPath: String,
    @SerialName("release_date")
    val releaseDate: String,
    @SerialName("title")
    val title: String,
    @SerialName("video")
    val video: Boolean,
    @SerialName("vote_average")
    val voteAverage: Double,
    @SerialName("vote_count")
    val voteCount: Int
) {
    fun getFullImageUrl(): String {
        val baseUrl = "https://image.tmdb.org/t/p/w500"

        val imagePath = if (posterPath.isEmpty().not()) {
            posterPath
        } else {
            backdropPath
        }

        return baseUrl + imagePath
    }
}

