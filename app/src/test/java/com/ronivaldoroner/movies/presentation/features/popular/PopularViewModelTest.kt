package com.ronivaldoroner.movies.presentation.features.popular

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ronivaldoroner.movies.domain.base.state.ScreenState
import com.ronivaldoroner.movies.domain.base.usecase.ScreenStore
import com.ronivaldoroner.movies.domain.features.popular.PopularScreen
import com.ronivaldoroner.movies.domain.features.popular.PopularUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class PopularViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher: TestDispatcher = StandardTestDispatcher()

    private lateinit var viewModel: PopularViewModel
    private lateinit var mockPopularUseCase: PopularUseCase
    private lateinit var mockPopularScreenStore: ScreenStore<PopularScreen>

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        mockPopularUseCase = mockk()
        mockPopularScreenStore = mockk(relaxed = true)
        viewModel = PopularViewModel(mockPopularUseCase, mockPopularScreenStore)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `when viewModel is initialized, then execute popularScreenStore`() = runTest {
        // Given
        val expectedScreen = PopularScreen()
        every { mockPopularScreenStore.state.value } returns expectedScreen

        // When
        viewModel

        // Then
        coVerify { mockPopularScreenStore.execute(mockPopularUseCase) }
    }

    @Test
    fun `when viewModel is initialized, then screen should be equal to popularScreenStore state`() =
        runTest {
            // Given
            val expectedScreen: StateFlow<PopularScreen> = MutableStateFlow(PopularScreen(state = ScreenState.Success))
            every { mockPopularScreenStore.state } returns expectedScreen

            // When
            val actualScreen = viewModel.screen

            // Then
            assertEquals(expectedScreen.value.state, actualScreen.value.state)
        }

    @Test
    fun `when popularScreenStore execute throws exception, then exception should be handled`() =
        runTest {
            // Given
            val expectedScreen = PopularScreen(state = ScreenState.Error(RuntimeException("Error")))
            every { mockPopularScreenStore.state.value } returns expectedScreen
            coEvery { mockPopularScreenStore.execute(mockPopularUseCase) } throws RuntimeException("Error")

            // When
            viewModel

            // Then
            coVerify { mockPopularScreenStore.execute(mockPopularUseCase) }
        }
}