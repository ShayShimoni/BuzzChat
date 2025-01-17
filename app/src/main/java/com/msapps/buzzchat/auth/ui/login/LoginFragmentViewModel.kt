package com.msapps.buzzchat.auth.ui.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.msapps.buzzchat.auth.models.requests.SendOtpRequest
import com.msapps.buzzchat.auth.models.responses.SendOtpResponse
import com.msapps.buzzchat.auth.repositories.PhoneAuthRepository
import com.msapps.buzzchat.utils.Constants
import com.msapps.buzzchat.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import java.util.regex.Pattern

class LoginFragmentViewModel(
    private val phoneAuthRepository: PhoneAuthRepository
): ViewModel() {

    private val _sendOtpStatus = MutableSharedFlow<SendOtpResponse>()
    val sendOtpStatus = _sendOtpStatus.asSharedFlow()

    private val _sharedFlow = MutableSharedFlow<Throwable>()
    val sharedFlow = _sharedFlow.asSharedFlow()

    fun sendOtp(request: SendOtpRequest) {
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = phoneAuthRepository.sendOtp(request)) {
                is Result.Success -> {
                    Log.i(this@LoginFragmentViewModel::class.simpleName, "sendOtp: ${result.data}")
                    _sendOtpStatus.emit(result.data)
                }

                is Result.Failure -> {
                    Log.e(
                        this@LoginFragmentViewModel::class.simpleName,
                        "sendOtp: ${result.error.message}"
                    )
                    _sharedFlow.emit(result.error)
                }
            }
        }
    }

    fun verifyPhoneNumber(phoneNumber: String): Boolean {
        return Pattern.compile(Constants.PHONE_NUMBER_REGEX).matcher(phoneNumber).matches()
    }

    fun checkForNumberPrefix(phoneNumber: String) = if (phoneNumber.startsWith("0") && phoneNumber.length == 10) {
        phoneNumber.removePrefix("0")
    } else {
        phoneNumber
    }

}