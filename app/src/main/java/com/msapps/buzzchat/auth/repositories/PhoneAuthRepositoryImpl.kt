package com.msapps.buzzchat.auth.repositories

import com.msapps.buzzchat.auth.api.abstractions.PhoneAuthApi
import com.msapps.buzzchat.auth.models.requests.CheckUserRequest
import com.msapps.buzzchat.auth.models.requests.SendOtpRequest
import com.msapps.buzzchat.auth.models.requests.VerifyOtpRequest
import com.msapps.buzzchat.auth.models.responses.CheckUserResponse
import com.msapps.buzzchat.auth.models.responses.SendOtpResponse
import com.msapps.buzzchat.auth.models.responses.VerifyOtpResponse
import com.msapps.buzzchat.utils.Result
import retrofit2.HttpException

class PhoneAuthRepositoryImpl(
    private val phoneAuthApi: PhoneAuthApi
): PhoneAuthRepository {

    override suspend fun sendOtp(request: SendOtpRequest): Result<SendOtpResponse> {
        return try {
            Result.Success(data = phoneAuthApi.sendOtp(request))
        } catch (e: HttpException) {
            Result.Failure(e)
        }
    }

    override suspend fun verifyOtp(request: VerifyOtpRequest): Result<VerifyOtpResponse> {
        return try {
            Result.Success(data = phoneAuthApi.verifyOtp(request))
        } catch (e: HttpException) {
            Result.Failure(e)
        }
    }

    override suspend fun checkUser(request: CheckUserRequest): Result<CheckUserResponse> {
        return try {
            Result.Success(data = phoneAuthApi.checkUser(request))
        } catch (e: HttpException) {
            Result.Failure(e)
        }
    }
}