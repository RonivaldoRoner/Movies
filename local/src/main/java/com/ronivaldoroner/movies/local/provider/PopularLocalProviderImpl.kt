package com.ronivaldoroner.movies.local.provider

import com.ronivaldoroner.movies.domain.base.response.Response
import com.ronivaldoroner.movies.domain.features.popular.PopularLocalProvider
import com.ronivaldoroner.movies.domain.features.popular.PopularScreen
import com.ronivaldoroner.movies.local.base.CacheData
import com.ronivaldoroner.movies.local.base.SessionId
import com.ronivaldoroner.movies.local.dao.MoviesCacheDao
import kotlinx.serialization.json.Json

class PopularLocalProviderImpl(
    private val moviesCacheDao: MoviesCacheDao
) : PopularLocalProvider {

    //TODO Abstrair para uma classe abstrata para que tenha apenas uma implementação generica
    override suspend fun saveCache(popularScreen: PopularScreen) {
        val movie = Json.encodeToString(popularScreen)

        moviesCacheDao.saveMoviesCache(
            CacheData(
                sessionId = sessionIdTemp,
                data = movie
            )
        )
    }

    override suspend fun getCache(): Response<PopularScreen> {
        return runCatching {
            val cache = moviesCacheDao.getMoviesCache(sessionIdTemp.id)
            val popularScreen = Json.decodeFromString<PopularScreen>(cache?.data ?: "")
            Response.Success(popularScreen)
        }.getOrElse {
            Response.Failure(it)
        }
    }

    //TODO Essa informação virá como parametro ou por injeção de dependencia
    private val sessionIdTemp = SessionId(
        userId = "1",
        featureId = "popular"
    )
}