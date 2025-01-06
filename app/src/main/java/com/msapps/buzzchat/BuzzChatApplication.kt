package com.msapps.buzzchat

import android.app.Application
import android.os.Build
import com.google.android.material.color.DynamicColors
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class BuzzChatApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        checkForDynamicColors()
        initKoin()
    }

    private fun checkForDynamicColors() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S && DynamicColors.isDynamicColorAvailable()) {
            DynamicColors.applyToActivitiesIfAvailable(this)
        }
    }

    private fun initKoin() {
        startKoin {
            androidLogger()
            androidContext(this@BuzzChatApplication)
            modules(appModule)
        }
    }
}