package com.ronivaldoroner.movies.remote.features.latest


import com.ronivaldoroner.movies.domain.features.latest.MoviesLatestScreen
import com.ronivaldoroner.movies.remote.constants.Route
import retrofit2.http.GET

interface MoviesLatestService {
    @GET(Route.GET_MOVIES_LATEST)
    suspend fun getMoviesLatest(): Result<MoviesLatestScreen>
}