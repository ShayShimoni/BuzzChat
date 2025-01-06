package com.msapps.buzzchat.auth.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.msapps.buzzchat.auth.models.requests.CheckUserRequest
import com.msapps.buzzchat.auth.models.requests.SendOtpRequest
import com.msapps.buzzchat.auth.repositories.abstractions.PhoneAuthRepository
import com.msapps.buzzchat.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginFragmentViewModel(
    private val phoneAuthRepository: PhoneAuthRepository
): ViewModel() {

    companion object {
        private val TAG = LoginFragmentViewModel::class.java.simpleName
    }

    fun sendOtp(request: SendOtpRequest) {
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = phoneAuthRepository.sendOtp(request)) {
                is Result.Success -> {
                    Log.i(TAG, "sendOtp: ${result.data}")
                }

                is Result.Failure -> {
                    Log.e(TAG, "sendOtp: ${result.error.message}")
                }
            }
        }
    }

    fun checkUser(request: CheckUserRequest) {
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = phoneAuthRepository.checkUser(request)) {
                is Result.Success -> {
                    Log.i(TAG, "checkUser: ${result.data}")
                }

                is Result.Failure -> {
                    Log.e(TAG, "checkUser: ${result.error.message}")
                }
            }
        }
    }
}