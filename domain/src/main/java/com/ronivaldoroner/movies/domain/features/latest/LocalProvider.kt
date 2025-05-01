package com.ronivaldoroner.movies.domain.features.latest

import com.ronivaldoroner.movies.domain.base.response.Response

interface LocalProvider {
    suspend fun getMoviesLatest(): Response<MoviesLatestScreen>
}