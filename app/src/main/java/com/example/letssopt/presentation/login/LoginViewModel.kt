package com.example.letssopt.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.letssopt.R
import com.example.letssopt.data.remote.RetrofitClient
import com.example.letssopt.data.remote.dto.request.SignInRequestDto
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
    private val _uiState = MutableStateFlow(LoginFormState())
    val uiState: StateFlow<LoginFormState> = _uiState.asStateFlow()

    private val _uiStates = MutableStateFlow<LoginUiState>(LoginUiState.Idle)
    val uiStates: StateFlow<LoginUiState> = _uiStates.asStateFlow()

    fun onIdChanged(value: String) {
        _uiState.update { it.copy(loginId = value) }
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
        .map { it.loginId.isNotBlank() && it.password.isNotBlank() }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = false
        )

    fun onLoginClick() {
        val idText = uiState.value.loginId
        val passwordText = uiState.value.password

        handleLogin(idText, passwordText)
    }

    private fun handleLogin(
        idText: String,
        passwordText: String,
    ) = viewModelScope.launch{
        _uiStates.value = LoginUiState.Loading

        runCatching {
            RetrofitClient.apiService.signIn(
                SignInRequestDto(idText, passwordText)
            )
        }.onSuccess { response ->
            if (response.isSuccessful){
                _uiStates.value = LoginUiState.Success
                sendEffect(LoginUiEffect.ShowToast(R.string.login_msg_success))
                sendEffect(LoginUiEffect.NavigateToMain)
            } else {
                val message = response.body()?.message ?: "로그인에 실패했습니다"
                _uiStates.value = LoginUiState.Error(message)
                sendEffect(LoginUiEffect.ShowToast(R.string.login_msg_fail))
            }
        }.onFailure { e ->
            _uiStates.value = LoginUiState.Error(e.message ?: "네트워크 오류가 발생했습니다")
        }
    }

    // 회원가입 텍스트 클릭
    fun onRegisterClick() {
        _uiState.update { it.copy(loginId = "", password = "") }
        sendEffect(LoginUiEffect.NavigateToRegister)
    }
}