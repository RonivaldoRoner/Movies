package com.ronivaldoroner.movies.local.di

import androidx.room.Room
import com.ronivaldoroner.movies.domain.features.popular.PopularLocalProvider
import com.ronivaldoroner.movies.local.database.MoviesDatabase
import com.ronivaldoroner.movies.local.provider.PopularLocalProviderImpl
import org.koin.dsl.module

val localModule = module {


    single {
        MoviesDatabase.getDatabase(get())
    }

    single { get<MoviesDatabase>().moviesCacheDao() }

    factory<PopularLocalProvider> { PopularLocalProviderImpl(get()) }
}