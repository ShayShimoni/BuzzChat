package com.msapps.buzzchat.core.di

import com.msapps.buzzchat.auth.di.authModule
import org.koin.core.module.Module

val appModule: List<Module> = listOf(authModule)