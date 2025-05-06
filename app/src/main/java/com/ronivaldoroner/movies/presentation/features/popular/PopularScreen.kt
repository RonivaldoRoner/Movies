package com.ronivaldoroner.movies.presentation.features.popular

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells.Adaptive
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.ronivaldoroner.movies.domain.base.state.ScreenState
import com.ronivaldoroner.movies.domain.features.popular.PopularScreen
import com.ronivaldoroner.movies.presentation.ui.components.ScreenStateResource
import com.ronivaldoroner.movies.presentation.ui.theme.MoviesTheme
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun PopularScreen(
    modifier: Modifier = Modifier,
    viewModel: PopularViewModel = koinViewModel(),
) {
    val screen by viewModel.screen.collectAsState()

    PopularContent(
        screen = screen,
        modifier = modifier
    )
}

@Composable
fun PopularContent(
    screen: PopularScreen,
    modifier: Modifier = Modifier
) {
    ScreenStateResource(
        state = screen.state,
        modifier = modifier
    ) { innerModifier ->
        LazyVerticalStaggeredGrid(
            columns = Adaptive(200.dp),
            verticalItemSpacing = 4.dp,
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            modifier = innerModifier.then(Modifier.weight(1f))
        ) {
            items(items = screen.popularPage?.movies ?: listOf()) { movie ->
                PopularMovieItem(
                    movie = movie, modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                )
            }
        }
    }
}

@Composable
@Preview
private fun PopularPreview(
    @PreviewParameter(PopularPreviewParameterProvider::class) screen: PopularScreen
) {
    MoviesTheme {
        PopularContent(
            screen = screen,
            modifier = Modifier.fillMaxSize()
        )
    }
}

class PopularPreviewParameterProvider : PreviewParameterProvider<PopularScreen> {
    override val values: Sequence<PopularScreen>
        get() = sequenceOf(
            PopularScreen(),
            PopularScreen(state = ScreenState.Success),
            PopularScreen(state = ScreenState.Error(Throwable("Error")))
        )
}