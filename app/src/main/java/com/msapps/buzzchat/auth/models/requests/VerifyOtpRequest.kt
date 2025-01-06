package com.msapps.buzzchat.auth.models.requests

data class VerifyOtpRequest(
    val sessionInfo: String,
    val code: String
)