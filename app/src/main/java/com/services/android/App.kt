package com.services.android

import android.app.Application
import com.example.servicesApplication.BuildConfig
import com.services.android.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        initDi()
    }

    private fun initDi() {
        startKoin {
            if (BuildConfig.DEBUG) {
                // do not use Level.DEBUG until issue is fixed:
                // https://github.com/InsertKoinIO/koin/issues/1188
                androidLogger(Level.ERROR)
            }
            androidContext(this@App)
            modules(appModules)
        }
    }
}
