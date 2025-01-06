package com.msapps.buzzchat

import com.msapps.buzzchat.auth.di.authModule
import com.msapps.buzzchat.splash.di.splashModule
import org.koin.core.module.Module

val appModule: List<Module> = listOf(
    authModule,
    splashModule
)