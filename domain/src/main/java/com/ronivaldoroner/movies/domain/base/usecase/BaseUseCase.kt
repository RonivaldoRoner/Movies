package com.ronivaldoroner.movies.domain.base.usecase

import com.ronivaldoroner.movies.domain.base.response.Notification
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel

interface BaseUseCase<T> {
     suspend fun preExecute(
        current: T,
        notification: Channel<Notification>,
        dispatcher: Dispatchers
    ): T = current

    suspend fun execute(
        current: T,
        notification: Channel<Notification>,
        dispatcher: Dispatchers
    ): T

     suspend fun posExecute(
        current: T,
        notification: Channel<Notification>,
        dispatcher: Dispatchers
    ): T = current
}