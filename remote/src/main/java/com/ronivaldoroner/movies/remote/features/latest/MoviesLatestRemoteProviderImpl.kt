package com.ronivaldoroner.movies.remote.features.latest

import com.ronivaldoroner.movies.domain.base.response.Response
import com.ronivaldoroner.movies.domain.features.latest.MoviesLatestRemoteProvider
import com.ronivaldoroner.movies.domain.features.latest.MoviesLatestScreen

class MoviesLatestRemoteProviderImpl(
    private val service: MoviesLatestService
) : MoviesLatestRemoteProvider {
    override suspend fun getMoviesLatest(): Response<MoviesLatestScreen> {
        val response = service.getMoviesLatest()

        return runCatching {
            Response.Success(response.getOrThrow())
        }.getOrElse {
            Response.Failure(it)
        }
    }
}