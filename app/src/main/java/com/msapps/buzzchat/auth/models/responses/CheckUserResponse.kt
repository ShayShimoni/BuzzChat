package com.msapps.buzzchat.auth.models.responses

data class CheckUserResponse(val users: List<UserInfo>)

data class UserInfo(
    val localId: String,
    val phoneNumber: String
)