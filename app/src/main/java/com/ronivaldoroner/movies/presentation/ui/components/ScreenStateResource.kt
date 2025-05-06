package com.ronivaldoroner.movies.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.ronivaldoroner.movies.R
import com.ronivaldoroner.movies.domain.base.state.ScreenState


@Composable
fun ScreenStateResource(
    state: ScreenState,
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.(Modifier) -> Unit
) {
    Column(
        modifier = modifier.then(
            Modifier
                .fillMaxSize()
                .scrollable(
                    state = rememberScrollState(),
                    enabled = true,
                    orientation = Orientation.Vertical
                )
        ),
        horizontalAlignment = CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        when (state) {
            is ScreenState.Loading -> LoadingScreen(modifier)
            is ScreenState.Success -> content(modifier)
            is ScreenState.Error -> ErrorScreen(state.throwable, modifier)
        }
    }
}


@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    CircularProgressIndicator(modifier = modifier.then(Modifier.size(40.dp)))
}

@Composable
fun ErrorScreen(throwable: Throwable, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Image(
            modifier = Modifier.size(40.dp),
            painter = painterResource(R.drawable.error_box),
            contentDescription = ""
        )
        Text(
            text = throwable.message ?: "Error",
            modifier = modifier
        )
    }
}

