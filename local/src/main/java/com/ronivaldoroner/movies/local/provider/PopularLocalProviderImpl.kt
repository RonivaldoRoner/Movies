package com.ronivaldoroner.movies.local.provider

import com.ronivaldoroner.movies.domain.base.cache.CacheLocalProvider
import com.ronivaldoroner.movies.domain.base.response.Response
import com.ronivaldoroner.movies.domain.features.popular.PopularScreen
import com.ronivaldoroner.movies.domain.utils.id
import com.ronivaldoroner.movies.local.base.CacheData
import com.ronivaldoroner.movies.local.dao.MoviesCacheDao
import kotlinx.serialization.json.Json

class PopularLocalProviderImpl(
    private val moviesCacheDao: MoviesCacheDao
) : CacheLocalProvider<PopularScreen> {

    override suspend fun saveCache(popularScreen: PopularScreen) {
        val movie = Json.encodeToString(popularScreen)

        moviesCacheDao.saveMoviesCache(
            CacheData(
                id = popularScreen.id,
                data = movie
            )
        )
    }

    override suspend fun getCache(id: String): Response<PopularScreen> {
        return runCatching {
            val cache = moviesCacheDao.getMoviesCache(id)
            val popularScreen = Json.decodeFromString<PopularScreen>(cache?.data ?: "")
            Response.Success(popularScreen)
        }.getOrElse {
            Response.Failure(it)
        }
    }
}