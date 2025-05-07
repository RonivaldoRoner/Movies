package com.ronivaldoroner.movies.domain.features.popular

import com.ronivaldoroner.movies.domain.base.response.Response

interface PopularLocalProvider {
    suspend fun getCache(): Response<PopularScreen>
    suspend fun saveCache(popularScreen: PopularScreen)
}