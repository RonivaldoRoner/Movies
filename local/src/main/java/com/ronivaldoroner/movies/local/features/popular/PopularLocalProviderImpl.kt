package com.ronivaldoroner.movies.local.features.popular

import com.ronivaldoroner.movies.domain.base.response.Response
import com.ronivaldoroner.movies.domain.features.popular.PopularLocalProvider
import com.ronivaldoroner.movies.domain.features.popular.PopularScreen

class PopularLocalProviderImpl: PopularLocalProvider  {
    override suspend fun getPopular(): Response<PopularScreen> {
        TODO("Not yet implemented")
    }
}