package com.ronivaldoroner.movies.local.di

import com.ronivaldoroner.movies.domain.features.popular.PopularLocalProvider
import com.ronivaldoroner.movies.local.features.popular.PopularLocalProviderImpl
import org.koin.dsl.module

val localModule = module {
    factory<PopularLocalProvider> { PopularLocalProviderImpl() }
}