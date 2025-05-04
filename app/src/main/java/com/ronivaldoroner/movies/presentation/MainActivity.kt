package com.ronivaldoroner.movies.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ronivaldoroner.movies.R
import com.ronivaldoroner.movies.presentation.features.popular.PopularScreen
import com.ronivaldoroner.movies.presentation.features.popular.PopularViewModel
import com.ronivaldoroner.movies.presentation.ui.components.MoviesTopBar
import com.ronivaldoroner.movies.presentation.ui.components.SplashScreen
import com.ronivaldoroner.movies.presentation.ui.theme.MoviesTheme
import com.ronivaldoroner.movies.presentation.utils.collectAsResource
import org.koin.compose.viewmodel.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController: NavHostController = rememberNavController()
            val backStackEntry by navController.currentBackStackEntryAsState()
            val currentScreen = MoviesRoute.valueOf(
                backStackEntry?.destination?.route ?: MoviesRoute.Splash.name
            )

            MoviesTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        MoviesTopBar(
                            currentScreen = currentScreen,
                            canNavigateBack = currentScreen != MoviesRoute.Splash,
                            navigateUp = { navController.navigateUp() }
                        )
                    }
                ) { innerPadding ->
                    NavHostSetup(
                        navController = navController,
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState())
                            .padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun NavHostSetup(navController: NavHostController, modifier: Modifier = Modifier) = NavHost(
    navController = navController,
    startDestination = MoviesRoute.Splash.name,
    modifier = modifier
) {
    composable(route = MoviesRoute.Splash.name) {
        SplashScreen(
            routes = MoviesRoute.entries.filter { it != MoviesRoute.Splash },
            onButtonClick = {
                navController.navigate(it.name)
            },
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
        )
    }
    composable(route = MoviesRoute.Popular.name) {
        PopularScreen(
            modifier = Modifier.fillMaxWidth()
        )
    }
}


enum class MoviesRoute(@StringRes val title: Int) {
    Splash(title = R.string.app_name),
    Popular(title = R.string.popular_movies),
}