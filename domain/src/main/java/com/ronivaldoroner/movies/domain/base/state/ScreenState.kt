package com.ronivaldoroner.movies.domain.base.state

import kotlinx.serialization.Serializable

@Serializable
sealed class ScreenState {
    object Loading : ScreenState()
    object Success : ScreenState()
    data class Error(val throwable: Throwable) : ScreenState()
}