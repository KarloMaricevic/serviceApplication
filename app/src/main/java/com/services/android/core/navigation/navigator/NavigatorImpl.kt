package com.services.android.core.navigation.navigator

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

class NavigatorImpl: Navigator {

    private val _navigationFlow = MutableSharedFlow<NavigationData>(extraBufferCapacity = 1)
    override val navigationFlow = _navigationFlow.asSharedFlow()


    override fun navigateTo(route: String, options: Navigator.Options.Forward?) {
        if (route.isNotEmpty()) {
            _navigationFlow.tryEmit(NavigationData(route, options))
        }
    }

    override fun navigateUp(options: Navigator.Options.Backward?) {
        val navigationOptions = options ?: Navigator.Options.Backward.Default
        _navigationFlow.tryEmit(NavigationData("", navigationOptions))
    }
}
