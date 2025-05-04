package com.ronivaldoroner.movies.presentation.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ronivaldoroner.movies.presentation.MoviesRoute
import com.ronivaldoroner.movies.presentation.ui.theme.MoviesTheme

@Composable
fun SplashScreen(
    routes: List<MoviesRoute>,
    onButtonClick: (MoviesRoute) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround,
    ) {
        routes.forEach { item ->
            Button(
                onClick = { onButtonClick(item) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(stringResource(item.title))
            }
        }
    }
}

@Composable
@Preview
private fun SplashPreview() {
    MoviesTheme {
        SplashScreen(
            routes = MoviesRoute.entries.toList(),
            onButtonClick = {},
            modifier = Modifier.fillMaxSize().padding(24.dp)
        )
    }
}