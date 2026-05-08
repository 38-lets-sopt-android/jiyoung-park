package com.example.letssopt.presentation.signup

import android.util.Patterns
import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.letssopt.R
import com.example.letssopt.data.auth.AuthRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

private enum class RegisterValidationError(@param:StringRes val message: Int) {
    EMAIL_INVALID(R.string.signup_msg_fail_emailinvalid),
    PASSWORD_INVALID_LENGTH(R.string.signup_msg_fail_passwordlength),
    PASSWORD_MISMATCH(R.string.signup_msg_fail_passwordmismatch)
}


class SignUpViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(SignUpFormState())
    val uiState: StateFlow<SignUpFormState> = _uiState.asStateFlow()

    private val _uiStates = MutableStateFlow<SignUpUiState>(SignUpUiState.Idle)
    val uiStates: StateFlow<SignUpUiState> = _uiStates.asStateFlow()

    fun onEmailChanged(value: String) {
        _uiState.update { it.copy(email = value) }
    }

    fun onPasswordChanged(value: String) {
        _uiState.update { it.copy(password = value) }
    }

    fun onPasswordConfirmChanged(value: String) {
        _uiState.update { it.copy(passwordConfirm = value) }
    }

    private val _uiEffect = MutableSharedFlow<SignUpUiEffect>()
    val uiEffect: SharedFlow<SignUpUiEffect> = _uiEffect.asSharedFlow()

    private fun sendEffect(effect: SignUpUiEffect) {
        viewModelScope.launch {
            _uiEffect.emit(effect)
        }
    }

    val registerEnabled: StateFlow<Boolean> = uiState
        .map { it.email.isNotBlank() && it.password.isNotBlank() && it.passwordConfirm.isNotBlank() }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = false
        )

    fun onSignUpClick() {
        val emailText = uiState.value.email
        val passwordText = uiState.value.password
        val passwordConfirmText = uiState.value.passwordConfirm

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
                sendEffect(SignUpUiEffect.ShowToast(R.string.signup_msg_success))
                sendEffect(SignUpUiEffect.BackToLogin)
            }
            .onFailure {
                sendEffect(SignUpUiEffect.ShowToast(R.string.signup_msg_fail))
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
