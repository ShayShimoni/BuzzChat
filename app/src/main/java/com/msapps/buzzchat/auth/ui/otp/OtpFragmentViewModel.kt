package com.msapps.buzzchat.auth.ui.otp

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.msapps.buzzchat.auth.models.requests.VerifyOtpRequest
import com.msapps.buzzchat.auth.models.responses.VerifyOtpResponse
import com.msapps.buzzchat.auth.repositories.PhoneAuthRepository
import com.msapps.buzzchat.auth.storage.UserSharedPreferences
import com.msapps.buzzchat.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class OtpFragmentViewModel(
    private val phoneAuthRepository: PhoneAuthRepository,
    private val userSharedPreferences: UserSharedPreferences
): ViewModel() {

    private val _verifyOtpStatus = MutableSharedFlow<VerifyOtpResponse>()
    val verifyOtpStatus = _verifyOtpStatus.asSharedFlow()

    private val _eventsSharedFlow = MutableSharedFlow<Throwable>()
    val eventsSharedFlow = _eventsSharedFlow.asSharedFlow()

    fun verifyOtp(request: VerifyOtpRequest) {
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = phoneAuthRepository.verifyOtp(request)) {
                is Result.Success -> {
                    Log.i(this@OtpFragmentViewModel::class.simpleName, "verifyOtp: ${result.data}")
                    _verifyOtpStatus.emit(result.data)
                    userSharedPreferences.setIsLoggedIn(true)
                }

                is Result.Failure -> {
                    Log.e(
                        this@OtpFragmentViewModel::class.simpleName,
                        "sendOtp: ${result.error.message}"
                    )
                    _eventsSharedFlow.emit(result.error)
                }
            }
        }
    }

    fun saveCredentials(
        idToken: String,
        refreshToken: String,
        expiredIn: String,
        phoneNumber: String,
        isNewUser: Boolean
    ) {
        userSharedPreferences.run {
            setIdToken(idToken)
            setRefreshToken(refreshToken)
            setExpiresIn(expiredIn)
            setPhoneNumber(phoneNumber)
            setIsNewUser(isNewUser)
        }
    }
}