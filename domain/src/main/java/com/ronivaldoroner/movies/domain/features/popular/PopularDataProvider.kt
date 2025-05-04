package com.ronivaldoroner.movies.domain.features.popular

import com.ronivaldoroner.movies.domain.base.response.Response

interface PopularDataProvider {
    suspend fun getPopular(isConnected: Boolean): Response<PopularScreen>
}