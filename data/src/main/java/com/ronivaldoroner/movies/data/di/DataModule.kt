package com.ronivaldoroner.movies.data.di

import com.ronivaldoroner.movies.data.features.popular.PopularDataProviderImpl
import com.ronivaldoroner.movies.domain.features.popular.PopularDataProvider
import org.koin.dsl.module

val dataModule = module {
    factory<PopularDataProvider> { PopularDataProviderImpl(get(), get()) }
}