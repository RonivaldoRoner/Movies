package com.ronivaldoroner.movies.presentation.features.popular

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.ronivaldoroner.movies.domain.base.state.ScreenState
import com.ronivaldoroner.movies.domain.features.popular.PopularScreen
import com.ronivaldoroner.movies.presentation.ui.components.ScreenStateResource
import com.ronivaldoroner.movies.presentation.ui.theme.MoviesTheme
import com.ronivaldoroner.movies.presentation.utils.collectAsResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun PopularScreen(
    viewModel: PopularViewModel = koinViewModel(),
    modifier: Modifier = Modifier,
) {
    val screen = viewModel.screen.collectAsResource()

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
        Text(
            text = "Popular",
            modifier = innerModifier
        )
    }
}

@Composable
@Preview
private fun PopularPreview(
    @PreviewParameter(PopularPreviewParameterProvider::class) screen: PopularScreen
) {
    MoviesTheme {
        PopularContent(
            screen = PopularScreen(state = ScreenState.Success),
            modifier = Modifier.fillMaxSize()
        )
    }
}

class PopularPreviewParameterProvider : PreviewParameterProvider<PopularScreen>{
    override val values: Sequence<PopularScreen>
        get() = sequence {
            PopularScreen()
            PopularScreen(state = ScreenState.Success)
            PopularScreen(state = ScreenState.Error(Throwable("Error")))
        }

}