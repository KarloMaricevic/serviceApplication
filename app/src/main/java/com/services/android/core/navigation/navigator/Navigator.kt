package com.services.android.core.navigation.navigator

import kotlinx.coroutines.flow.SharedFlow

interface Navigator {

    fun navigateTo(route: String, options: Options.Forward? = null)
    fun navigateUp(options: Options.Backward? = null)

    val navigationFlow: SharedFlow<NavigationData>

    sealed class Options {
        data class Forward(
            val launchSingleTop: Boolean = false,
            val clearBackstack: Boolean = false
        ) : Options()

        sealed class Backward : Options() {
            object Default : Backward()
            data class Custom(
                val route: String,
                val inclusive: Boolean
            ) : Backward()
        }
    }
}
