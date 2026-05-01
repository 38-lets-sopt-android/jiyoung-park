package com.example.letssopt.presentation.login

import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.clearText
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.letssopt.R
import com.example.letssopt.data.auth.AuthException
import com.example.letssopt.data.auth.AuthRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    private val _uiEffect = MutableSharedFlow<LoginUiEffect>()
    val uiEffect: SharedFlow<LoginUiEffect> = _uiEffect.asSharedFlow()

    private fun sendEffect(effect: LoginUiEffect) {
        viewModelScope.launch {
            _uiEffect.emit(effect)
        }
    }

    // 이메일, 비밀번호 입력 상태
    val emailState = TextFieldState()
    val passwordState = TextFieldState()

    // 모두 입력됐을 때만 true -> 로그인 버튼 활성화
    val loginEnabled by derivedStateOf { emailState.text.isNotBlank() && passwordState.text.isNotBlank() }

    fun onLoginClick() {
        val emailText = emailState.text.toString()
        val passwordText = passwordState.text.toString()

        handleLogin(emailText, passwordText)
    }

    private fun handleLogin(
        emailText: String,
        passwordText: String,
    ) {
        AuthRepository.login(emailText, passwordText)
            .onSuccess {_: Unit ->
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
        emailState.clearText()
        passwordState.clearText()
        sendEffect(LoginUiEffect.NavigateToRegister)
    }
}