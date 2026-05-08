package com.example.letssopt.presentation.signup

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.letssopt.R
import com.example.letssopt.core.commom.extension.toast
import com.example.letssopt.core.commom.util.HandleUiEffects
import com.example.letssopt.core.designsystem.component.LetsLabeledTextField

@Composable
fun SignUpRoute(
    popBackStack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: SignUpViewModel = viewModel(),
) {
    val context = LocalContext.current
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val registerEnabled by viewModel.registerEnabled.collectAsStateWithLifecycle()

    HandleUiEffects(viewModel.uiEffect) { effect ->
        when (effect) {
            SignUpUiEffect.BackToLogin -> popBackStack()

            is SignUpUiEffect.ShowToast -> context.toast(effect.message)
        }
    }

    SignUpScreen(
        uiState = uiState,
        registerEnabled = registerEnabled,
        onRegisterClick = viewModel::onSignUpClick,
        onEmailChanged = viewModel::onEmailChanged,
        onPasswordChanged = viewModel::onPasswordChanged,
        onPasswordConfirmChanged = viewModel::onPasswordConfirmChanged,
        modifier = modifier,
    )
}

@Composable
private fun SignUpScreen(
    onEmailChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onPasswordConfirmChanged: (String) -> Unit,
    uiState: SignUpFormState,
    registerEnabled: Boolean,
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
            modifier = Modifier
                .fillMaxWidth(),
            color = Color(0xFFE8003C),
            fontSize = 36.sp,
            fontFamily = FontFamily(Font(R.font.pretendard_bold)),
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(26.dp))

        Text(
            text = "회원가입",
            modifier = Modifier.fillMaxWidth(),
            color = Color.White,
            fontSize = 20.sp,
            fontFamily = FontFamily(Font(R.font.pretendard_bold)),
            fontWeight = FontWeight.Bold,
        )

        Spacer(modifier = Modifier.height(36.dp))

        LetsLabeledTextField(
            value = uiState.email,
            onValueChange = onEmailChanged,
            label = "이메일",
            placeholder = "이메일 주소를 입력하세요",
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next,
            ),
        )

        Spacer(modifier = Modifier.height(18.dp))

        LetsLabeledTextField(
            value = uiState.password,
            onValueChange = onPasswordChanged,
            label = "비밀번호",
            placeholder = "비밀번호를 입력하세요",
            isPassword = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Next,
            ),
        )

        Spacer(modifier = Modifier.height(18.dp))

        LetsLabeledTextField(
            value = uiState.passwordConfirm,
            onValueChange = onPasswordConfirmChanged,
            label = "비밀번호 확인",
            placeholder = "비밀번호를 다시 입력하세요",
            isPassword = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Next,
            ),
        )

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = onRegisterClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            enabled = registerEnabled,
            shape = RoundedCornerShape(size = 8.dp),
            colors = ButtonDefaults.buttonColors(
                contentColor = Color(0xFFFFFFFF),
                disabledContentColor = Color(0xFF666666),
                containerColor = Color(0xFFE8003C),
                disabledContainerColor = Color(0xFF333333)
            )
        ) {
            Text(
                text = "회원가입",
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.pretendard_bold)),
                fontWeight = FontWeight.Bold
            )

        }

        Spacer(modifier = Modifier.height(26.dp))

    }

}