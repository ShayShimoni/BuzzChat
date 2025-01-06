package com.msapps.buzzchat.auth.models.responses

data class CheckUserResponse(val users: List<UserInfo>)

data class UserInfo(
    val localId: String,
    val email: String?,
    val phoneNumber: String?,
    val displayName: String?,
    val emailVerified: Boolean
)