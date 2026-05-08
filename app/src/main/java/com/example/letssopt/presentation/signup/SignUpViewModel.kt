package com.example.letssopt.presentation.signup

import android.util.Patterns
import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.letssopt.R
import com.example.letssopt.data.remote.RetrofitClient
import com.example.letssopt.data.remote.dto.request.SignUpRequestDto
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

    fun onIdChanged(value: String) {
        _uiState.update { it.copy(loginId = value) }
    }

    fun onPasswordChanged(value: String) {
        _uiState.update { it.copy(password = value) }
    }

    fun onPasswordConfirmChanged(value: String) {
        _uiState.update { it.copy(passwordConfirm = value) }
    }

    fun onNameChanged(value: String) {
        _uiState.update { it.copy(name = value) }
    }

    fun onEmailChanged(value: String) {
        _uiState.update { it.copy(email = value) }
    }

    fun onAgeChanged(value: String) {
        _uiState.update { it.copy(age = value) }
    }

    fun onPartChanged(value: String) {
        _uiState.update { it.copy(part = value) }
    }

    private val _uiEffect = MutableSharedFlow<SignUpUiEffect>()
    val uiEffect: SharedFlow<SignUpUiEffect> = _uiEffect.asSharedFlow()

    private fun sendEffect(effect: SignUpUiEffect) {
        viewModelScope.launch {
            _uiEffect.emit(effect)
        }
    }

    val registerEnabled: StateFlow<Boolean> = uiState
        .map {
            it.loginId.isNotBlank() && it.password.isNotBlank()
                    && it.passwordConfirm.isNotBlank() && it.name.isNotBlank()
                    && it.email.isNotBlank() && it.age.isNotBlank()
                    && it.part.isNotBlank() }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = false
        )

    fun onSignUpClick() {
        val idText = uiState.value.loginId
        val passwordText = uiState.value.password
        val passwordConfirmText = uiState.value.passwordConfirm
        val nameText = uiState.value.name
        val emailText = uiState.value.email
        val ageText = uiState.value.age.toInt()
        val partText = uiState.value.part

        val error = validateRegisterInputs(
            passwordText = passwordText,
            passwordConfirmText = passwordConfirmText,
            emailText = emailText,
        )
        if (error != null) {
            return sendEffect(SignUpUiEffect.ShowToast(error.message))
        }

        handleRegister(
            idText = idText,
            passwordText = passwordText,
            nameText = nameText,
            emailText = emailText,
            ageText = ageText,
            partText = partText,
        )
    }

    private fun handleRegister(
        idText: String,
        passwordText: String,
        nameText: String,
        emailText: String,
        ageText: Int,
        partText: String,
    ) = viewModelScope.launch {
        _uiStates.value = SignUpUiState.Loading

        runCatching {
            RetrofitClient.apiService.signUp(
                SignUpRequestDto(idText, passwordText, nameText, emailText, ageText, partText)
            )
        }.onSuccess { response ->
            if (response.isSuccessful) {
                _uiStates.value = SignUpUiState.Success
                sendEffect(SignUpUiEffect.ShowToast(R.string.signup_msg_success))
                sendEffect(SignUpUiEffect.BackToLogin)
            } else {
                val message = response.body()?.message ?: "회원가입에 실패했습니다"
                _uiStates.value = SignUpUiState.Error(message)
                sendEffect(SignUpUiEffect.ShowToast(R.string.signup_msg_fail))
            }
        }.onFailure { e ->
            _uiStates.value = SignUpUiState.Error(e.message ?: "네트워크 오류가 발생했습니다")
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
