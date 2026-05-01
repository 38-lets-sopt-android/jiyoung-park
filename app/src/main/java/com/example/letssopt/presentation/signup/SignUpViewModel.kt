package com.example.letssopt.presentation.signup

import android.util.Patterns
import androidx.annotation.StringRes
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.letssopt.R
import com.example.letssopt.data.auth.AuthRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

private enum class RegisterValidationError(@param:StringRes val message: Int) {
    EMAIL_INVALID(R.string.register_msg_fail_emailinvalid),
    PASSWORD_INVALID_LENGTH(R.string.register_msg_fail_passwordlength),
    PASSWORD_MISMATCH(R.string.register_msg_fail_passwordmismatch)
}


class SignUpViewModel : ViewModel() {
    private val _uiEffect = MutableSharedFlow<SignUpUiEffect>()
    val uiEffect: SharedFlow<SignUpUiEffect> = _uiEffect.asSharedFlow()

    private fun sendEffect(effect: SignUpUiEffect) {
        viewModelScope.launch {
            _uiEffect.emit(effect)
        }
    }

    val emailState = TextFieldState()
    val passwordState = TextFieldState()
    val passwordConfirmState = TextFieldState()

    val registerEnabled by derivedStateOf {
        emailState.text.isNotBlank() && passwordState.text.isNotBlank() && passwordConfirmState.text.isNotBlank()
    }

    fun onSignUpClick() {
        val emailText = emailState.text.toString()
        val passwordText = passwordState.text.toString()
        val passwordConfirmText = passwordConfirmState.text.toString()

        val error = validateRegisterInputs(emailText, passwordText, passwordConfirmText)
        if (error != null) {
            return sendEffect(SignUpUiEffect.ShowToast(error.message))
        }

        handleRegister(emailText, passwordText)
    }

    private fun handleRegister(
        emailText: String,
        passwordText: String,
    ) {
        AuthRepository.register(emailText, passwordText)
            .onSuccess {
                sendEffect(SignUpUiEffect.ShowToast(R.string.register_msg_success))
                sendEffect(SignUpUiEffect.BackToLogin)
            }
            .onFailure {
                sendEffect(SignUpUiEffect.ShowToast(R.string.register_msg_fail))
            }
    }

    private fun validateRegisterInputs(
        emailText: String,
        passwordText: String,
        passwordConfirmText: String,
    ): RegisterValidationError? {
        return when {
            !Patterns.EMAIL_ADDRESS.matcher(emailText)
                .matches() -> RegisterValidationError.EMAIL_INVALID

            passwordText.length !in 8..12 -> RegisterValidationError.PASSWORD_INVALID_LENGTH
            passwordText != passwordConfirmText -> RegisterValidationError.PASSWORD_MISMATCH
            else -> null
        }
    }
}
