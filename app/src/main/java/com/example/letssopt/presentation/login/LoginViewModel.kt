package com.example.letssopt.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.letssopt.R
import com.example.letssopt.data.auth.AuthException
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

class LoginViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    fun onEmailChanged(value: String) {
        _uiState.update { it.copy(email = value) }
    }

    fun onPasswordChanged(value: String) {
        _uiState.update { it.copy(password = value) }
    }
    private val _uiEffect = MutableSharedFlow<LoginUiEffect>()
    val uiEffect: SharedFlow<LoginUiEffect> = _uiEffect.asSharedFlow()

    private fun sendEffect(effect: LoginUiEffect) {
        viewModelScope.launch {
            _uiEffect.emit(effect)
        }
    }

    // 모두 입력됐을 때만 true -> 로그인 버튼 활성화
    val loginEnabled: StateFlow<Boolean> = uiState
        .map { it.email.isNotBlank() && it.password.isNotBlank() }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = false
        )

    fun onLoginClick() {
        val emailText = uiState.value.email
        val passwordText = uiState.value.password

        handleLogin(emailText, passwordText)
    }

    private fun handleLogin(
        emailText: String,
        passwordText: String,
    ) {
        AuthRepository.login(emailText, passwordText)
            .onSuccess {
                sendEffect(LoginUiEffect.ShowToast(R.string.login_msg_success))
                sendEffect(LoginUiEffect.NavigateToMain)
            }
            .onFailure { error ->
                val message = if (error is AuthException) {
                    when (error) {
                        is AuthException.EmailNotFound -> R.string.login_msg_fail_emailnotfound
                        is AuthException.NoAccountFound -> R.string.login_msg_fail_needregister
                        is AuthException.PasswordMismatch -> R.string.login_msg_fail_passwordmismatch
                    }
                } else R.string.login_msg_fail
                sendEffect(LoginUiEffect.ShowToast(message))
            }
    }

    // 회원가입 텍스트 클릭
    fun onRegisterClick() {
        _uiState.update { it.copy(email = "", password = "") }
        sendEffect(LoginUiEffect.NavigateToRegister)
    }
}