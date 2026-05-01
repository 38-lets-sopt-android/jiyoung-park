package com.example.letssopt.data.auth

import android.content.Context
import android.content.SharedPreferences
import com.example.letssopt.R

object AuthRepository {
    lateinit var prefs: SharedPreferences

    private const val PREFS_EMAIL_KEY = "emailKey"
    private const val PREFS_PASSWORD_KEY = "passwordKey"
    private const val PREFS_IS_LOGGED_IN_KEY = "isLoggedInKey"

    fun isLoggedIn() = prefs.getBoolean(PREFS_IS_LOGGED_IN_KEY, false)

    fun init(context: Context) {
        prefs = context.getSharedPreferences(
            context.getString(R.string.shared_preference_file_key),
            Context.MODE_PRIVATE
        )
    }

    fun register(email: String, password: String): Result<Unit> {
        return runCatching {
            prefs.edit()
                .putString(PREFS_EMAIL_KEY, email)
                .putString(PREFS_PASSWORD_KEY, password)
                .apply()
        }
    }

    fun login(email: String, password: String): Result<Unit> {
        return runCatching {
            val savedEmail = prefs.getString(PREFS_EMAIL_KEY, null)
            val savedPassword = prefs.getString(PREFS_PASSWORD_KEY, null)

            when {
                savedEmail == null || savedPassword == null -> throw AuthException.NoAccountFound()
                savedEmail != email -> throw AuthException.EmailNotFound()
                savedPassword != password -> throw AuthException.PasswordMismatch()
                else -> saveLoggedInState()
            }
        }
    }

    private fun saveLoggedInState() {
        prefs.edit().putBoolean(PREFS_IS_LOGGED_IN_KEY, true).apply()
    }

    fun logout() {
        prefs.edit().putBoolean(PREFS_IS_LOGGED_IN_KEY, false).apply()
    }
}
