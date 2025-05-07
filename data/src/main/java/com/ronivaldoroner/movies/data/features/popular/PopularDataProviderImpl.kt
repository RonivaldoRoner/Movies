package com.ronivaldoroner.movies.data.features.popular

import com.ronivaldoroner.movies.domain.base.response.Response
import com.ronivaldoroner.movies.domain.features.popular.PopularDataProvider
import com.ronivaldoroner.movies.domain.base.cache.CacheLocalProvider
import com.ronivaldoroner.movies.domain.features.popular.PopularRemoteProvider
import com.ronivaldoroner.movies.domain.features.popular.PopularScreen
import com.ronivaldoroner.movies.domain.utils.id
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PopularDataProviderImpl(
    private val remoteProvider: PopularRemoteProvider,
    private val localProvider: CacheLocalProvider<PopularScreen>
) : PopularDataProvider {

    override suspend fun getPopular(isConnected: Boolean): Response<PopularScreen> {
        return if (isConnected) {
            when (val remoteResponse = remoteProvider.getPopular()) {
                is Response.Success -> {
                    saveResponse(remoteResponse.value)

                    remoteResponse
                }

                is Response.Failure -> {
                    tryCacheRecover(remoteResponse)
                }
            }
        } else {
            localProvider.getCache(PopularScreen().id)
        }
    }

    private fun saveResponse(remoteResponse: PopularScreen) =
        CoroutineScope(Dispatchers.IO).launch {
            localProvider.saveCache(remoteResponse)
        }

    private suspend fun tryCacheRecover(remoteResponse: Response<PopularScreen>) =
        when (val localResponse = localProvider.getCache(PopularScreen().id)) {
            is Response.Success -> {
                localResponse
            }

            is Response.Failure -> {
                remoteResponse
            }

        }


}