package com.ronivaldoroner.movies.domain.features.popular

import com.ronivaldoroner.movies.domain.base.response.Notification
import com.ronivaldoroner.movies.domain.base.response.Response
import com.ronivaldoroner.movies.domain.base.state.ScreenState
import com.ronivaldoroner.movies.domain.base.usecase.BaseUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel

class PopularUseCase(
    private val popularDataProvider: PopularDataProvider
) : BaseUseCase<PopularScreen> {

    override suspend fun preExecute(
        current: PopularScreen,
        notification: Channel<Notification>,
        dispatcher: Dispatchers
    ): PopularScreen {
        return current.copy(state = ScreenState.Loading)
    }

    override suspend fun execute(
        current: PopularScreen,
        notification: Channel<Notification>,
        dispatcher: Dispatchers
    ): PopularScreen {
        return when (val response = popularDataProvider.getPopular(isConnected = true)) {
            is Response.Success -> {
                current.copy(
                    state = ScreenState.Success,
                    popularPage = response.value.popularPage
                )
            }

            is Response.Failure -> {
                current.copy(
                    state = ScreenState.Error(response.throwable),
                    popularPage = null
                )
            }
        }
    }
}