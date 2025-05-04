package com.ronivaldoroner.movies.presentation.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.ronivaldoroner.movies.domain.base.state.State
import kotlinx.coroutines.flow.StateFlow

@Composable
fun <T : State> StateFlow<T>.collectAsResource(): T {
    val newValue = this.collectAsState().value
    val resource by remember { mutableStateOf(newValue, policy = resourceStatusPolicy()) }
    return resource
}
