package com.ronivaldoroner.movies.local.di

import com.ronivaldoroner.movies.domain.base.cache.CacheLocalProvider
import com.ronivaldoroner.movies.domain.features.popular.PopularScreen
import com.ronivaldoroner.movies.local.database.MoviesDatabase
import com.ronivaldoroner.movies.local.provider.PopularLocalProviderImpl
import org.koin.dsl.module

val localModule = module {


    single {
        MoviesDatabase.getDatabase(get())
    }

    single { get<MoviesDatabase>().moviesCacheDao() }

    factory<CacheLocalProvider<PopularScreen>> { PopularLocalProviderImpl(get()) }
}