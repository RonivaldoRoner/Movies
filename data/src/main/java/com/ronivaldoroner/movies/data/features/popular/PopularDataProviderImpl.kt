package com.ronivaldoroner.movies.data.features.popular

import com.ronivaldoroner.movies.domain.base.response.Response
import com.ronivaldoroner.movies.domain.features.popular.PopularDataProvider
import com.ronivaldoroner.movies.domain.features.popular.PopularLocalProvider
import com.ronivaldoroner.movies.domain.features.popular.PopularRemoteProvider
import com.ronivaldoroner.movies.domain.features.popular.PopularScreen

class PopularDataProviderImpl(
    private val remoteProvider: PopularRemoteProvider,
    private val localProvider: PopularLocalProvider
) : PopularDataProvider {

    override suspend fun getPopular(isConnected: Boolean): Response<PopularScreen> {
        return if (isConnected) {
            when (val remoteResponse = remoteProvider.getPopular()) {
                is Response.Success -> {
                    localProvider.saveCache(remoteResponse.value)
                    remoteResponse
                }

                is Response.Failure -> {
                    when (val localResponse = localProvider.getCache()) {
                        is Response.Success -> {
                            localResponse
                        }

                        is Response.Failure -> {
                            remoteResponse
                        }

                    }
                }
            }
        } else {
            localProvider.getCache()
        }
    }
}