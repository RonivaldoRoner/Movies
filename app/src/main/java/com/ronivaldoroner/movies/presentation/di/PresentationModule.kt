package com.ronivaldoroner.movies.presentation.di

import com.ronivaldoroner.movies.presentation.features.popular.popularKoinModule
import org.koin.dsl.module

val presentationModules = listOf(
    popularKoinModule
)