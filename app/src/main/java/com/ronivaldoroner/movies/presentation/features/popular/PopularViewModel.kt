package com.ronivaldoroner.movies.presentation.features.popular

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ronivaldoroner.movies.domain.base.usecase.ScreenStore
import com.ronivaldoroner.movies.domain.features.popular.PopularScreen
import com.ronivaldoroner.movies.domain.features.popular.PopularUseCase
import kotlinx.coroutines.launch

class PopularViewModel(
    private val popularUseCase: PopularUseCase,
    private val popularScreenStore: ScreenStore<PopularScreen>,
) : ViewModel() {

    val screen get() = popularScreenStore.state

    init {
        viewModelScope.launch {
            popularScreenStore.execute(popularUseCase)
        }
    }
}