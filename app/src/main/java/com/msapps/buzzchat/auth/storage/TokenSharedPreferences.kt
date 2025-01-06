package com.msapps.buzzchat.auth.storage

import android.content.Context
import com.msapps.buzzchat.utils.Constants

class TokenSharedPreferences(context: Context) {

    private val preferences = context.getSharedPreferences(
        Constants.AUTH_TOKEN_PREF_FILE_NAME,
        Context.MODE_PRIVATE
    )
    private val editor = preferences.edit()

    fun setIdToken(idToken: String) {
        editor.putString(Constants.USER_ID_TOKEN_KEY, idToken).apply()
    }

    fun setRefreshToken(refreshToken: String) {
        editor.putString(Constants.USER_REFRESH_TOKEN_KEY, refreshToken).apply()
    }

    fun getIdToken() = preferences.getString(Constants.USER_ID_TOKEN_KEY, "").orEmpty()

    fun getRefreshToken() = preferences.getString(Constants.USER_REFRESH_TOKEN_KEY, "").orEmpty()

    fun clearPrefs(){
        editor.clear()
    }
}