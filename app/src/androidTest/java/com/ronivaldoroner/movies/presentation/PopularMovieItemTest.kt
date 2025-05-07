package com.ronivaldoroner.movies.presentation

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.ronivaldoroner.movies.domain.features.popular.Movie
import com.ronivaldoroner.movies.presentation.features.popular.PopularMovieItem
import com.ronivaldoroner.movies.presentation.ui.theme.MoviesTheme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PopularMovieItemTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val testMovie = Movie(
        adult = false,
        backdropPath = "null",
        genreIds = emptyList(),
        id = 1,
        originalLanguage = "en",
        originalTitle = "Test Original Title",
        overview = "This is a long overview that should be truncated initially. This part should only be visible when expanded.",
        popularity = 100.0,
        posterPath = "/test_poster.jpg",
        releaseDate = "2023-10-27",
        title = "Test Movie Title",
        video = false,
        voteAverage = 7.5,
        voteCount = 1000
    )

    @Test
    fun popularMovieItem_displaysTitleAndReleaseDate() {
        composeTestRule.setContent {
            MoviesTheme {
                PopularMovieItem(movie = testMovie)
            }
        }

        composeTestRule.onNodeWithText("Test Movie Title").assertIsDisplayed()
        composeTestRule.onNodeWithText("2023-10-27").assertIsDisplayed()
    }

    @Test
    fun popularMovieItem_overviewIsInitiallyTruncated() {
        composeTestRule.setContent {
            MoviesTheme {
                PopularMovieItem(movie = testMovie)
            }
        }

        composeTestRule.onNodeWithText(testMovie.overview, substring = true).assertDoesNotExist()
        composeTestRule.onNodeWithText("This is a long overview", substring = true)
            .assertIsDisplayed()
    }

    @Test
    fun popularMovieItem_clickingOverviewExpandsAndCollapses() {
        composeTestRule.setContent {
            MoviesTheme {
                PopularMovieItem(movie = testMovie)
            }
        }

        composeTestRule.onNodeWithText("This is a long overview", substring = true).performClick()
        composeTestRule.onNodeWithText(testMovie.overview, substring = true).assertIsDisplayed()
        composeTestRule.onNodeWithText(testMovie.overview, substring = true).performClick()
        composeTestRule.onNodeWithText(testMovie.overview, substring = true).assertDoesNotExist()
    }

    @Test
    fun popularMovieItem_clickingChevronExpandsAndCollapses() {
        composeTestRule.setContent {
            MoviesTheme {
                PopularMovieItem(movie = testMovie)
            }
        }

        composeTestRule.onNodeWithText("This is a long overview", substring = true).performClick()
        composeTestRule.onNodeWithText(testMovie.overview, substring = true).assertIsDisplayed()
        composeTestRule.onNodeWithTag("chevron_icon").performClick()
        composeTestRule.onNodeWithText(testMovie.overview, substring = true).assertDoesNotExist()
    }

    @Test
    fun popularMovieItem_displaysAsyncImage() {
        composeTestRule.setContent {
            MoviesTheme {
                PopularMovieItem(movie = testMovie)
            }
        }

        composeTestRule.onNodeWithContentDescription("Test Original Title").assertIsDisplayed()
    }
}