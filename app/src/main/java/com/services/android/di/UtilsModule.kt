package com.services.android.di

import com.services.android.utils.LoggerUtil
import com.services.android.utils.services.BasicService
import org.koin.dsl.module

val utilsModule = module {
    factory { LoggerUtil() }
}