package com.ronivaldoroner.movies.remote.features.popular

import com.ronivaldoroner.movies.domain.base.response.Response
import com.ronivaldoroner.movies.domain.features.popular.PopularRemoteProvider
import com.ronivaldoroner.movies.domain.features.popular.PopularScreen

class PopularRemoteProviderImpl(
    private val service: PopularService
) : PopularRemoteProvider {

    override suspend fun getPopular(): Response<PopularScreen> = runCatching {
        val response = service.getPopular()
        if (response.isSuccessful) {
            val page = response.body()!!
            Response.Success(PopularScreen(popularPage = page))
        } else {
            //TODO Tratar mensagem de erro para um feedback amigável
            Response.Failure(Throwable(response.toString()))
        }
    }.getOrElse {
        Response.Failure(it)
    }
}