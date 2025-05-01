package com.ronivaldoroner.movies.remote.di

import com.ronivaldoroner.movies.domain.features.latest.MoviesLatestRemoteProvider
import com.ronivaldoroner.movies.remote.features.latest.MoviesLatestRemoteProviderImpl
import com.ronivaldoroner.movies.remote.features.latest.MoviesLatestService
import com.ronivaldoroner.movies.remote.network.RetrofitService
import org.koin.dsl.module

val networkModule = module{

    //region RetrofitServices
    single { RetrofitService().createWebService(MoviesLatestService::class.java) }
    //endregion

    //region RemoteProviders
    single<MoviesLatestRemoteProvider> { MoviesLatestRemoteProviderImpl(get()) }
    //endregion
}