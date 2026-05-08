package com.example.letssopt.presentation.signup

import androidx.annotation.StringRes
import com.example.letssopt.core.base.UiEffect

data class SignUpFormState(
    val loginId: String = "",
    val email: String = "",
    val password: String = "",
    val passwordConfirm: String = "",
    val name: String = "",
    val age: String = "",
    val part: String = "",
)

sealed interface SignUpUiEffect: UiEffect {
    data object BackToLogin: SignUpUiEffect
    data class ShowToast(@param:StringRes val message: Int): SignUpUiEffect
}