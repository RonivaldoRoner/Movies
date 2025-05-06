package com.ronivaldoroner.movies.presentation.features.popular

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.compose.rememberAsyncImagePainter
import com.ronivaldoroner.movies.R
import com.ronivaldoroner.movies.domain.features.popular.Movie
import com.ronivaldoroner.movies.presentation.ui.theme.MoviesTheme
import kotlinx.serialization.json.Json


@Composable
fun PopularMovieItem(
    movie: Movie,
    modifier: Modifier = Modifier
) {
    val (expandOverview, setExpandOverview) = remember { mutableStateOf(false) }
    Card(
        modifier = modifier,
        elevation = CardDefaults.elevatedCardElevation(4.dp)
    ) {


        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Row(
                modifier = Modifier
                    .padding(16.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                AsyncImage(
                    placeholder = painterResource(R.drawable.loading),
                    error = painterResource(R.drawable.error_box),
                    fallback = painterResource(R.drawable.placeholder),
                    model = movie.getFullImageUrl(),
                    contentDescription = movie.originalTitle,
                    modifier = Modifier.weight(1f)
                )

                Spacer(modifier = Modifier.size(16.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Text(text = movie.title, fontWeight = FontWeight.Bold)
                    Text(text = movie.releaseDate, modifier = Modifier.padding(top = 8.dp))
                }
            }

            Text(
                text = movie.overview,
                maxLines = if (expandOverview) Int.MAX_VALUE else 2,
                modifier = Modifier
                    .padding(top = 8.dp)
                    .clickable { setExpandOverview(expandOverview.not()) }
                    .animateContentSize()
            )
            Spacer(modifier = Modifier.height(4.dp))

            Image(
                modifier = Modifier
                    .size(24.dp)
                    .clickable { setExpandOverview(expandOverview.not()) },
                painter = painterResource(if (expandOverview) R.drawable.chevron_up else R.drawable.chevron_down),
                contentDescription = ""
            )
        }
    }

}

@Composable
@Preview
private fun PopularMovieItemPreview(
    @PreviewParameter(PopularMovieItemPreviewParameterProvider::class) movie: Movie
) {
    MoviesTheme {
        PopularMovieItem(
            movie,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )
    }
}

class PopularMovieItemPreviewParameterProvider : PreviewParameterProvider<Movie> {
    override val values: Sequence<Movie>
        get() = sequenceOf(movie)

    val movieJson = """{
      "adult": false,
      "backdrop_path": "/gMJngTNfaqCSCqGD4y8lVMZXKDn.jpg",
      "genre_ids": [
        28,
        12,
        878
      ],
      "id": 640146,
      "original_language": "en",
      "original_title": "Ant-Man and the Wasp: Quantumania",
      "overview": "Super-Hero partners Scott Lang and Hope van Dyne, along with with Hope's parents Janet van Dyne and Hank Pym, and Scott's daughter Cassie Lang, find themselves exploring the Quantum Realm, interacting with strange new creatures and embarking on an adventure that will push them beyond the limits of what they thought possible.",
      "popularity": 8567.865,
      "poster_path": "/ngl2FKBlU4fhbdsrtdom9LVLBXw.jpg",
      "release_date": "2023-02-15",
      "title": "Ant-Man and the Wasp: Quantumania",
      "video": false,
      "vote_average": 6.5,
      "vote_count": 1886
    }""".trimIndent()

    val movie: Movie = Json.decodeFromString(movieJson)

}