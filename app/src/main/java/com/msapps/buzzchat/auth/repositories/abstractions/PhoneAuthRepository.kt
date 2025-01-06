package com.msapps.buzzchat.auth.repositories.abstractions

import com.msapps.buzzchat.auth.models.requests.CheckUserRequest
import com.msapps.buzzchat.auth.models.requests.SendOtpRequest
import com.msapps.buzzchat.auth.models.requests.VerifyOtpRequest
import com.msapps.buzzchat.auth.models.responses.CheckUserResponse
import com.msapps.buzzchat.auth.models.responses.SendOtpResponse
import com.msapps.buzzchat.auth.models.responses.VerifyOtpResponse
import com.msapps.buzzchat.core.utils.Result

interface PhoneAuthRepository {
    suspend fun sendOtp(request: SendOtpRequest): Result<SendOtpResponse>
    suspend fun verifyOtp(request: VerifyOtpRequest): Result<VerifyOtpResponse>
    suspend fun checkUser(request: CheckUserRequest): Result<CheckUserResponse>
}