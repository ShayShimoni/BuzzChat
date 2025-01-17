package com.msapps.buzzchat.auth.api

import com.msapps.buzzchat.BuildConfig
import com.msapps.buzzchat.auth.models.requests.CheckUserRequest
import com.msapps.buzzchat.auth.models.requests.SendOtpRequest
import com.msapps.buzzchat.auth.models.requests.VerifyOtpRequest
import com.msapps.buzzchat.auth.models.responses.CheckUserResponse
import com.msapps.buzzchat.auth.models.responses.SendOtpResponse
import com.msapps.buzzchat.auth.models.responses.VerifyOtpResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface PhoneAuthApiImpl: PhoneAuthApi {
    @POST("accounts%3AsendVerificationCode?key=${BuildConfig.FIREBASE_API_KEY}")
    override suspend fun sendOtp(@Body request: SendOtpRequest): SendOtpResponse

    @POST("accounts%3AsignInWithPhoneNumber?key=${BuildConfig.FIREBASE_API_KEY}")
    override suspend fun verifyOtp(@Body request: VerifyOtpRequest): VerifyOtpResponse

    @POST("accounts%3Alookup?key=${BuildConfig.FIREBASE_API_KEY}")
    override suspend fun checkUser(@Body request: CheckUserRequest): CheckUserResponse
}