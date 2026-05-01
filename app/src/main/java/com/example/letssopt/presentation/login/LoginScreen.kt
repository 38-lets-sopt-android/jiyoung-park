package com.example.letssopt.presentation.login

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.letssopt.R
import com.example.letssopt.core.commom.extension.toast
import com.example.letssopt.core.commom.util.HandleUiEffects

@Composable
fun LoginRoute(
    navigateToRegister: ()->Unit,
    navigateToMain: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel = viewModel(),
) {
    val context = LocalContext.current

    HandleUiEffects(viewModel.uiEffect) { effect ->
        when (effect) {
            LoginUiEffect.NavigateToRegister -> navigateToRegister()

            LoginUiEffect.NavigateToMain -> navigateToMain()

            is LoginUiEffect.ShowToast -> context.toast(effect.message)
        }
    }

    LoginScreen(
        emailState = viewModel.emailState,
        passwordState = viewModel.passwordState,
        loginEnabled = viewModel.loginEnabled,
        onLoginClick = viewModel::onLoginClick,
        onRegisterClick = viewModel::onRegisterClick,
        modifier = modifier,
    )
}

@Composable
private fun LoginScreen(
    emailState: TextFieldState,
    passwordState: TextFieldState,
    loginEnabled: Boolean,
    onLoginClick: () -> Unit,
    onRegisterClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(horizontal = 20.dp)
    ) {
        Spacer(modifier = Modifier.height(60.dp))

        Text(
            text = "watcha",
            modifier = Modifier.fillMaxWidth(),
            color = Color(0xFFE8003C),
            fontSize = 36.sp,
            fontFamily = FontFamily(Font(R.font.pretendard_bold)),
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(26.dp))

        Text(
            text = "이메일로 로그인",
            modifier = Modifier.fillMaxWidth(),
            color = Color.White,
            fontSize = 20.sp,
            fontFamily = FontFamily(Font(R.font.pretendard_bold)),
            fontWeight = FontWeight.Bold,
        )

        Spacer(modifier = Modifier.height(36.dp))

        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "이메일",
                modifier = Modifier.fillMaxWidth(),
                color = Color(0xFF999999),
                fontSize = 14.sp,
                fontFamily = FontFamily(Font(R.font.pretendard_regular)),
                fontWeight = FontWeight.Medium,
            )
            TextField(
                value = emailState.text.toString(),
                onValueChange = {
                    emailState.edit { replace(0, length, it) }
                },
                modifier = Modifier.fillMaxWidth(),
                placeholder = {
                    Text(
                        text = "이메일 주소를 입력하세요",
                        color = Color(0xFF666666),
                        fontSize = 14.sp,
                        fontFamily = FontFamily(Font(R.font.pretendard_regular)),
                        fontWeight = FontWeight.Medium
                    )
                },
                shape = RoundedCornerShape(size = 8.dp),
                colors = TextFieldDefaults.colors(
                    disabledTextColor = Color(0xFF666666),
                    unfocusedTextColor = Color(0xFFFFFFFF),
                    focusedTextColor = Color(0xFFFFFFFF),
                    unfocusedContainerColor = Color(0xFF2A2A2A),
                    focusedContainerColor = Color(0xFF2A2A2A),
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent
                )
            )
        }

        Spacer(modifier = Modifier.height(18.dp))

        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "비밀번호",
                modifier = Modifier.fillMaxWidth(),
                color = Color(0xFF999999),
                fontSize = 14.sp,
                fontFamily = FontFamily(Font(R.font.pretendard_regular)),
                fontWeight = FontWeight.Medium
            )
            TextField(
                value = passwordState.text.toString(),
                onValueChange = {
                    passwordState.edit { replace(0, length, it) }
                },
                modifier = Modifier.fillMaxWidth(),
                placeholder = {
                    Text(
                        text = "비밀번호를 입력하세요",
                        color = Color(0xFF666666),
                        fontSize = 14.sp,
                        fontFamily = FontFamily(Font(R.font.pretendard_regular)),
                        fontWeight = FontWeight.Medium
                    )
                },
                visualTransformation = PasswordVisualTransformation(mask = '*'),
                shape = RoundedCornerShape(size = 8.dp),
                colors = TextFieldDefaults.colors(
                    disabledTextColor = Color(0xFF666666),
                    unfocusedTextColor = Color(0xFFFFFFFF),
                    focusedTextColor = Color(0xFFFFFFFF),
                    unfocusedContainerColor = Color(0xFF2A2A2A),
                    focusedContainerColor = Color(0xFF2A2A2A),
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent
                )
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        Text(
            text = "아직 계정이 없으신가요?  회원가입",
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onRegisterClick() },
            color = Color(0xFF999999),
            fontSize = 14.sp,
            fontFamily = FontFamily(Font(R.font.pretendard_regular)),
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = onLoginClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            enabled = loginEnabled,
            shape = RoundedCornerShape(size = 8.dp),
            colors = ButtonDefaults.buttonColors(
                contentColor = Color(0xFFFFFFFF),
                disabledContentColor = Color(0xFF666666),
                containerColor = Color(0xFFE8003C),
                disabledContainerColor = Color(0xFF333333)
            )
        ) {
            Text(
                text = "로그인",
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.pretendard_bold)),
                fontWeight = FontWeight.Bold
            )

        }

        Spacer(modifier = Modifier.height(26.dp))

    }

}