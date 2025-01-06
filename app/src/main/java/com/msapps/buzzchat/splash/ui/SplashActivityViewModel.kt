package com.msapps.buzzchat.splash.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.msapps.buzzchat.auth.models.requests.CheckUserRequest
import com.msapps.buzzchat.auth.repositories.abstractions.PhoneAuthRepository
import com.msapps.buzzchat.auth.storage.TokenSharedPreferences
import com.msapps.buzzchat.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SplashActivityViewModel(
    private val phoneAuthRepository: PhoneAuthRepository,
    private val tokenSharedPreferences: TokenSharedPreferences
): ViewModel() {

    private val _loginState = MutableStateFlow(false)
    val loginState = _loginState.asStateFlow()

    fun checkLoginStatus() {
        viewModelScope.launch(Dispatchers.IO) {
            when(val result = phoneAuthRepository.checkUser(CheckUserRequest(tokenSharedPreferences.getIdToken()))) {
                is Result.Success -> {
                    _loginState.emit(true)
                }
                is Result.Failure -> {
                    Log.e(this::class.java.simpleName, "checkLoginStatus: ${result.error.message}")
                    _loginState.emit(false)
                }
            }
        }
    }
}