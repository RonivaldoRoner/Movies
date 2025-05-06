package com.ronivaldoroner.movies.domain.features.popular

import com.ronivaldoroner.movies.domain.base.response.Notification
import com.ronivaldoroner.movies.domain.base.response.Response
import com.ronivaldoroner.movies.domain.base.state.ScreenState
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class PopularUseCaseTest {

    private val testDispatcher: TestDispatcher = StandardTestDispatcher()
    private val dispatchers = Dispatchers
    private lateinit var popularUseCase: PopularUseCase
    private lateinit var mockPopularDataProvider: PopularDataProvider
    private lateinit var notificationChannel: Channel<Notification>

    @Before
    fun setup() {
        dispatchers.setMain(testDispatcher)
        mockPopularDataProvider = mockk()
        notificationChannel = Channel()
        popularUseCase = PopularUseCase(mockPopularDataProvider)
    }

    @After
    fun tearDown() {
        dispatchers.resetMain()
    }

    @Test
    fun `when preExecute is called, then state should be Loading`() = runTest {
        // Given
        val currentScreen = PopularScreen(state = ScreenState.Success)

        // When
        val result = popularUseCase.preExecute(currentScreen, notificationChannel, dispatchers)

        // Then
        assertEquals(ScreenState.Loading, result.state)
    }

    @Test
    fun `when execute is called and getPopular returns success, then state should be Success and popularPage should be set`() =
        runTest {
            // Given
            val currentScreen = PopularScreen(state = ScreenState.Loading)
            val expectedPopularPage = PopularPage(
                page = 1, movies = listOf(), totalPages = 1, totalResults = 1
            )
            val response = Response.Success(currentScreen.copy(popularPage = expectedPopularPage))

            coEvery { mockPopularDataProvider.getPopular(isConnected = true) } returns response

            // When
            val result = popularUseCase.execute(currentScreen, notificationChannel, dispatchers)

            // Then
            assertEquals(ScreenState.Success, result.state)
            assertEquals(expectedPopularPage, result.popularPage)
            coVerify(exactly = 1) { mockPopularDataProvider.getPopular(isConnected = true) }
        }

    @Test
    fun `when execute is called and getPopular returns failure, then state should be Error and popularPage should be null`() =
        runTest {
            // Given
            val currentScreen = PopularScreen(state = ScreenState.Loading)
            val expectedThrowable = Throwable("Error")
            val response = Response.Failure(expectedThrowable)

            coEvery { mockPopularDataProvider.getPopular(isConnected = true) } returns response

            // When
            val result = popularUseCase.execute(currentScreen, notificationChannel, dispatchers)

            // Then
            assertEquals(ScreenState.Error(expectedThrowable), result.state)
            assertEquals(null, result.popularPage)
            coVerify(exactly = 1) { mockPopularDataProvider.getPopular(isConnected = true) }
        }

    @Test
    fun `when execute is called, then getPopular should be called`() = runTest {
        // Given
        val currentScreen = PopularScreen(state = ScreenState.Loading)
        val expectedPopularPage =
            PopularPage(page = 1, movies = listOf(), totalPages = 1, totalResults = 1)
        val response = Response.Success(PopularScreen(popularPage = expectedPopularPage))

        coEvery { mockPopularDataProvider.getPopular(isConnected = true) } returns response

        // When
        popularUseCase.execute(currentScreen, notificationChannel, dispatchers)

        // Then
        coVerify(exactly = 1) { mockPopularDataProvider.getPopular(isConnected = true) }
    }
}