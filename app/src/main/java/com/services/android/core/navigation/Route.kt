package com.services.android.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry

interface Route {

    companion object {

        val screens = listOf<Route>()
    }

    val route: String

    @Composable
    fun Content(navBackStackEntry: NavBackStackEntry)
}
