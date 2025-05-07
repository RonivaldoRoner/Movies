package com.ronivaldoroner.movies.presentation.features.popular

import com.ronivaldoroner.movies.domain.base.usecase.ScreenStore
import com.ronivaldoroner.movies.domain.features.popular.PopularScreen
import com.ronivaldoroner.movies.domain.features.popular.PopularUseCase
import com.ronivaldoroner.movies.presentation.utils.isNetworkAvailable
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module

val popularKoinModule = module {
    factory { (initialState: PopularScreen) ->
        ScreenStore<PopularScreen>(initialState, Dispatchers)
    }

    factory { (isConnected: Boolean) ->
        PopularUseCase(
            isConnected = isConnected,
            popularDataProvider = get()
        )
    }

    viewModel {
        val store = get<ScreenStore<PopularScreen>> { parametersOf(PopularScreen()) }
        val isConnected = isNetworkAvailable(androidContext())

        PopularViewModel(
            popularUseCase = get { parametersOf(isConnected) },
            popularScreenStore = store
        )
    }
}