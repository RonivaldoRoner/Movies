package com.ronivaldoroner.movies.presentation.ui.components

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ronivaldoroner.movies.domain.base.state.ScreenState


@Composable
fun ScreenStateResource(
    state: ScreenState,
    modifier: Modifier = Modifier,
    content: @Composable (Modifier) -> Unit
) {
    when(state){
        is ScreenState.Loading -> LoadingScreen(modifier)
        is ScreenState.Success -> content(modifier)
        is ScreenState.Error -> ErrorScreen(state.throwable, modifier)
    }
}


@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    CircularProgressIndicator(modifier = modifier)
}

@Composable
fun ErrorScreen(throwable: Throwable, modifier: Modifier = Modifier) {
    Text(
        text = throwable.message ?: "Error",
        modifier = modifier
    )
}

