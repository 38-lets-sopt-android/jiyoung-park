package com.example.letssopt.presentation.login

import androidx.annotation.StringRes
import com.example.letssopt.core.base.UiEffect

data class LoginFormState(
    val loginId: String = "",
    val password: String = "",
)

sealed interface LoginUiEffect: UiEffect {
    data object NavigateToRegister: LoginUiEffect
    data object NavigateToMain: LoginUiEffect
    data class ShowToast(@param:StringRes val message: Int): LoginUiEffect
}