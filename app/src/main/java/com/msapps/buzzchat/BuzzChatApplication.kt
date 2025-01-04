package com.msapps.buzzchat

import android.app.Application
import com.msapps.buzzchat.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class BuzzChatApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@BuzzChatApplication)
            modules(appModule)
        }
    }
}