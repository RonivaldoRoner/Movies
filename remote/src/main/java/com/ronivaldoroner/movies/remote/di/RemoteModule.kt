package com.ronivaldoroner.movies.remote.di

import com.ronivaldoroner.movies.domain.features.latest.MoviesLatestRemoteProvider
import com.ronivaldoroner.movies.domain.features.popular.PopularRemoteProvider
import com.ronivaldoroner.movies.remote.features.latest.MoviesLatestRemoteProviderImpl
import com.ronivaldoroner.movies.remote.features.latest.MoviesLatestService
import com.ronivaldoroner.movies.remote.features.popular.PopularRemoteProviderImpl
import com.ronivaldoroner.movies.remote.features.popular.PopularService
import com.ronivaldoroner.movies.remote.network.RetrofitService
import org.koin.dsl.module

val remoteModule = module {

    //region RetrofitServices
    factory { RetrofitService.createWebService(PopularService::class.java) }
    factory { RetrofitService.createWebService(MoviesLatestService::class.java) }
    //endregion

    //region RemoteProviders
    single<PopularRemoteProvider> { PopularRemoteProviderImpl(get()) }
    single<MoviesLatestRemoteProvider> { MoviesLatestRemoteProviderImpl(get()) }
    //endregion

}