package com.msapps.buzzchat.splash.di

import com.msapps.buzzchat.auth.repositories.PhoneAuthRepository
import com.msapps.buzzchat.auth.storage.UserSharedPreferences
import com.msapps.buzzchat.splash.ui.SplashActivityViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val splashModule = module {

    viewModel<SplashActivityViewModel> {
        SplashActivityViewModel(
            get<PhoneAuthRepository>(),
            get<UserSharedPreferences>()
        )
    }
}