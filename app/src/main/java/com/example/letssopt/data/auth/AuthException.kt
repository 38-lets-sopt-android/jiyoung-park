package com.example.letssopt.data.auth

sealed class AuthException : Exception() {
    class NoAccountFound : AuthException()
    class EmailNotFound : AuthException()
    class PasswordMismatch : AuthException()
}
