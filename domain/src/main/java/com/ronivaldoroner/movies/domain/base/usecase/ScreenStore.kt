package com.ronivaldoroner.movies.domain.base.usecase

import com.ronivaldoroner.movies.domain.base.response.Notification
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.sync.Semaphore
import kotlinx.coroutines.sync.withPermit
import java.util.logging.Logger

class ScreenStore<T>(
    initialState: T,
    private val dispatchers: Dispatchers
) {

    private val _state = MutableStateFlow(initialState)
    val state: StateFlow<T> get() = _state

    private val currentState = state.value

    private val _notification = Channel<Notification>()
    val notification = _notification.receiveAsFlow()

    private val semaphore = Semaphore(permits = 1)

    suspend fun execute(useCase: BaseUseCase<T>) {
        semaphore.withPermit {
            updateState(action = useCase::preExecute)
            updateState(action = useCase::execute)
            updateState(action = useCase::posExecute)
        }
    }

    private suspend fun updateState(
        action: suspend (T, Channel<Notification>, Dispatchers) -> T
    ) {
        runCatching {
            val nextState = action(currentState, _notification, dispatchers)

            if (nextState != currentState) {
                _state.value = nextState
            }
        }.getOrElse {
            Logger.getLogger(it.message)
        }
    }
}