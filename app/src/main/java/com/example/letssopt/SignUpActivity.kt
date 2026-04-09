package com.example.letssopt

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.letssopt.ui.theme.LETSSOPTTheme
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.foundation.text.KeyboardOptions

class NextActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = androidx.activity.SystemBarStyle.dark(
                android.graphics.Color.TRANSPARENT
            ),
            navigationBarStyle = androidx.activity.SystemBarStyle.dark(
                android.graphics.Color.TRANSPARENT
            )
        )
        setContent {
            LETSSOPTTheme {
                Scaffold(modifier = Modifier.fillMaxSize(), containerColor = Color.Black) { innerPadding ->
                    NextGreeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun NextGreeting(name: String, modifier: Modifier = Modifier) {

    val context = LocalContext.current
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordCheck by remember { mutableStateOf("") }
    val allFilled = email.isNotEmpty() && password.isNotEmpty() && passwordCheck.isNotEmpty()

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

        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "회원가입",
                modifier = Modifier.fillMaxWidth(),
                color = Color.White,
                fontSize = 20.sp,
                fontFamily = FontFamily(Font(R.font.pretendard_bold)),
                fontWeight = FontWeight.Bold,

                )
        }

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
                value = email,
                onValueChange = {email = it},
                modifier = Modifier.fillMaxWidth(),
                placeholder = {Text(
                    text = "이메일 주소를 입력하세요",
                    modifier = Modifier.fillMaxWidth(),
                    color = Color(0xFF666666),
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.pretendard_regular)),
                    fontWeight = FontWeight.Medium)},
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

        Column (
            modifier = Modifier.fillMaxWidth()
        ){
            Text(
                text = "비밀번호",
                modifier = Modifier.fillMaxWidth(),
                color = Color(0xFF999999),
                fontSize = 14.sp,
                fontFamily = FontFamily(Font(R.font.pretendard_regular)),
                fontWeight = FontWeight.Medium
            )
            TextField(
                value = password,
                onValueChange = { password = it },
                modifier = Modifier.fillMaxWidth(),
                placeholder = {Text(
                    text = "비밀번호를 입력하세요",
                    modifier = Modifier.fillMaxWidth(),
                    color = Color(0xFF666666),
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.pretendard_regular)),
                    fontWeight = FontWeight.Medium)},
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

        Spacer(modifier = Modifier.height(18.dp))

        Column (
            modifier = Modifier.fillMaxWidth()
        ){
            Text(
                text = "비밀번호 확인",
                modifier = Modifier.fillMaxWidth(),
                color = Color(0xFF999999),
                fontSize = 14.sp,
                fontFamily = FontFamily(Font(R.font.pretendard_regular)),
                fontWeight = FontWeight.Medium
            )
            TextField(
                value = passwordCheck,
                onValueChange = { passwordCheck = it },
                modifier = Modifier.fillMaxWidth(),
                placeholder = {Text(
                    text = "비밀번호를 다시 입력하세요",
                    modifier = Modifier.fillMaxWidth(),
                    color = Color(0xFF666666),
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.pretendard_regular)),
                    fontWeight = FontWeight.Medium)},
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

        Spacer(modifier = Modifier.height(280.dp))

        Button(
            onClick = {
                if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    Toast.makeText(context,"이메일 형식이 올바르지 않습니다.", Toast.LENGTH_SHORT).show()
                }
                else if(password.length !in 8..12){
                    Toast.makeText(context,"비밀번호는 8~12자여야 합니다.", Toast.LENGTH_SHORT).show()
                }
                else if(password != passwordCheck){
                    Toast.makeText(context,"비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show()
                }
                else{
                    Toast.makeText(context,"회원가입 성공!", Toast.LENGTH_SHORT).show()

                    val activity = context as NextActivity
                    val resultIntent = Intent().apply {
                        putExtra("email",email)
                        putExtra("password",password)
                    }
                    activity.setResult(android.app.Activity.RESULT_OK, resultIntent)
                    activity.finish()
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            enabled = allFilled,
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

    }

}

@Preview(showBackground = true)
@Composable
fun NextGreetingPreview() {
    LETSSOPTTheme {
        NextGreeting("Android")
    }
}