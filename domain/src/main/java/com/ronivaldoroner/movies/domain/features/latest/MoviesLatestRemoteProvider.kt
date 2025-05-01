package com.ronivaldoroner.movies.domain.features.latest

import com.ronivaldoroner.movies.domain.base.response.Response

interface MoviesLatestRemoteProvider {
    suspend fun getMoviesLatest(): Response<MoviesLatestScreen>
}