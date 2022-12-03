package com.services.android.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.services.android.core.navigation.navigator.NavigationData
import com.services.android.core.navigation.navigator.Navigator
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.compose.get

@Composable
fun NavigationComposable(startDestination: String) {
    val navController = rememberNavController()

    RootNavHost(navController, startDestination)
    ObserveNavigationRequests(navController)
}

@Composable
private fun RootNavHost(navController: NavHostController, startDestination: String) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
    ) {
        Route.screens.forEach { screen ->
            composable(
                route = screen.route,
            ) {
                screen.Content(it)
            }
        }
    }
}

@Composable
private fun ObserveNavigationRequests(
    navController: NavHostController,
    navigator: Navigator = get()
) {
    LaunchedEffect(navController) {
        navigator.navigationFlow.onEach {
            executeNavigationRequests(navController, it)
        }.launchIn(this)
    }
}

private fun executeNavigationRequests(
    navController: NavHostController,
    navigationData: NavigationData,
) {
    when (val navOptions = navigationData.options) {
        is Navigator.Options.Backward.Custom -> {
            navController.popBackStack(
                route = navOptions.route,
                inclusive = navOptions.inclusive,
            )
        }
        is Navigator.Options.Backward.Default -> {
            navController.popBackStack()
        }
        else -> navController.navigate(navigationData.route) {
            if (navOptions is Navigator.Options.Forward) {
                navOptions.launchSingleTop.let { isLaunchSingleTop ->
                    launchSingleTop = isLaunchSingleTop
                }
                navOptions.clearBackstack.let { isClearBackStack ->
                    if (isClearBackStack) {
                        popUpTo(0) { inclusive = true }
                    }
                }
            }
        }
    }
}
