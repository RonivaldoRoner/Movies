package com.ronivaldoroner.movies.remote.features.popular

import com.ronivaldoroner.movies.domain.features.popular.PopularPage
import com.ronivaldoroner.movies.remote.constants.Route
import retrofit2.Response
import retrofit2.http.GET

interface PopularService {

    @GET(Route.GET_POPULAR)
    suspend fun getPopular(): Response<PopularPage>
}