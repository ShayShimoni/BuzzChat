package com.msapps.buzzchat.auth.di

import android.content.Context
import com.msapps.buzzchat.BuildConfig
import com.msapps.buzzchat.auth.api.PhoneAuthApi
import com.msapps.buzzchat.auth.api.PhoneAuthApiImpl
import com.msapps.buzzchat.auth.repositories.PhoneAuthRepository
import com.msapps.buzzchat.auth.repositories.PhoneAuthRepositoryImpl
import com.msapps.buzzchat.auth.storage.UserSharedPreferences
import com.msapps.buzzchat.auth.ui.login.LoginFragmentViewModel
import com.msapps.buzzchat.auth.ui.otp.OtpFragmentViewModel
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val authModule = module {
    single<PhoneAuthApi> {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val httpClient = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor { chain ->
                val original: Request = chain.request()
                val originalUrl: HttpUrl = original.url

                val newUrl = originalUrl.newBuilder()
                    .encodedPath(originalUrl.encodedPath.replace("%3A", ":"))
                    .build()

                val newRequest: Request = original.newBuilder()
                    .url(newUrl)
                    .build()
                chain.proceed(newRequest)
            }
            .build()

        Retrofit.Builder()
            .baseUrl(BuildConfig.FIREBASE_AUTH_BASE_URL)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PhoneAuthApiImpl::class.java)
    }

    single<PhoneAuthRepository> {
        PhoneAuthRepositoryImpl(get<PhoneAuthApi>())
    }

    single<UserSharedPreferences> {
        UserSharedPreferences(get<Context>())
    }

    viewModel<LoginFragmentViewModel> {
        LoginFragmentViewModel(get<PhoneAuthRepository>())
    }

    viewModel<OtpFragmentViewModel> {
        OtpFragmentViewModel(
            get<PhoneAuthRepository>(),
            get<UserSharedPreferences>()
        )
    }
}