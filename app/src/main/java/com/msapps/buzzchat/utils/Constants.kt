package com.msapps.buzzchat.utils

object Constants {

    // Shared Preference
    const val AUTH_USER_PREF_FILE_NAME = "UserPrefs"
    const val USER_ID_TOKEN_KEY = "idToken"
    const val USER_REFRESH_TOKEN_KEY = "refreshToken"
    const val USER_REFRESH_EXPIRES_IN = "expiresIn"
    const val USER_LOCAL_ID_KEY = "localId"
    const val USER_IS_NEW_USER_KEY = "isNewUser"
    const val USER_PHONE_NUMBER_KEY = "phoneNumber"
    const val USER_IS_LOGGED_IN_KEY = "isLoggedIn"

    // Bundle Keys
    const val EXTRA_IS_LOGGED_IN = "isLoggedIn"

    // Regex
    const val PHONE_NUMBER_REGEX = "^[0-9]{9,10}$"
}