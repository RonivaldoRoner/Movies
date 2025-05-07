package com.ronivaldoroner.movies.data.di

import com.ronivaldoroner.movies.data.features.popular.PopularDataProviderImpl
import com.ronivaldoroner.movies.domain.features.popular.PopularDataProvider
import com.ronivaldoroner.movies.local.di.localModule
import com.ronivaldoroner.movies.remote.di.remoteModule
import org.koin.core.module.Module
import org.koin.dsl.module

val dataModules: List<Module> get() = listOf(dataModule, remoteModule, localModule)

private val dataModule = module {
    factory<PopularDataProvider> { PopularDataProviderImpl(get()) }
}