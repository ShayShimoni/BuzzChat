package com.msapps.buzzchat.auth.models.responses

data class VerifyOtpResponse(
    val idToken: String,
    val refreshToken: String,
    val expiresIn: String,
    val localId: String,
    val isNewUser: Boolean,
    val phoneNumber: String
)