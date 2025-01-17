package com.msapps.buzzchat.auth.api

import com.msapps.buzzchat.auth.models.requests.CheckUserRequest
import com.msapps.buzzchat.auth.models.requests.SendOtpRequest
import com.msapps.buzzchat.auth.models.requests.VerifyOtpRequest
import com.msapps.buzzchat.auth.models.responses.CheckUserResponse
import com.msapps.buzzchat.auth.models.responses.SendOtpResponse
import com.msapps.buzzchat.auth.models.responses.VerifyOtpResponse

interface PhoneAuthApi {
    suspend fun sendOtp(request: SendOtpRequest): SendOtpResponse
    suspend fun verifyOtp(request: VerifyOtpRequest): VerifyOtpResponse
    suspend fun checkUser(request: CheckUserRequest): CheckUserResponse
}