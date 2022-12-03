package com.services.android.core

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

abstract class BaseViewModel : ViewModel() {

    abstract fun onEvent(event: Event)

    protected fun <T> MutableStateFlow<T>.setState(reducer: T.() -> T) {
        this.value = reducer(this.value)
    }
}
