package com.ronivaldoroner.movies.domain.base.cache

import com.ronivaldoroner.movies.domain.base.response.Response

interface CacheLocalProvider<T> {
    suspend fun getCache(id: String): Response<T>
    suspend fun saveCache(popularScreen: T)
}