package com.msapps.buzzchat.auth.storage

import android.content.Context
import com.msapps.buzzchat.utils.Constants

class UserSharedPreferences(context: Context) {

    private val preferences = context.getSharedPreferences(
        Constants.AUTH_USER_PREF_FILE_NAME,
        Context.MODE_PRIVATE
    )
    private val editor = preferences.edit()

    fun setIdToken(idToken: String) {
        editor.putString(Constants.USER_ID_TOKEN_KEY, idToken).apply()
    }

    fun setRefreshToken(refreshToken: String) {
        editor.putString(Constants.USER_REFRESH_TOKEN_KEY, refreshToken).apply()
    }

    fun setExpiresIn(expiresIn: String) {
        editor.putString(Constants.USER_REFRESH_EXPIRES_IN, expiresIn).apply()
    }

    fun setLocalId(localId: String) {
        editor.putString(Constants.USER_LOCAL_ID_KEY, localId).apply()
    }

    fun setIsNewUser(isNewUser: Boolean) {
        editor.putBoolean(Constants.USER_IS_NEW_USER_KEY, isNewUser).apply()
    }

    fun setPhoneNumber(phoneNumber: String) {
        editor.putString(Constants.USER_PHONE_NUMBER_KEY, phoneNumber).apply()
    }

    fun setIsLoggedIn(isLoggedIn: Boolean) {
        editor.putBoolean(Constants.USER_IS_LOGGED_IN_KEY, isLoggedIn).apply()
    }

    fun getIdToken() = preferences.getString(Constants.USER_ID_TOKEN_KEY, "").orEmpty()

    fun getRefreshToken() = preferences.getString(Constants.USER_REFRESH_TOKEN_KEY, "").orEmpty()

    fun getExpiresIn() = preferences.getString(Constants.USER_REFRESH_EXPIRES_IN, "").orEmpty()

    fun getLocalId() = preferences.getString(Constants.USER_LOCAL_ID_KEY, "").orEmpty()

    fun getIsNewUser() = preferences.getBoolean(Constants.USER_IS_NEW_USER_KEY, false)

    fun getPhoneNumber() = preferences.getString(Constants.USER_PHONE_NUMBER_KEY, "").orEmpty()

    fun getIsLoggedIn() = preferences.getBoolean(Constants.USER_IS_LOGGED_IN_KEY, false)

    fun clearPrefs() {
        editor.clear()
    }
}