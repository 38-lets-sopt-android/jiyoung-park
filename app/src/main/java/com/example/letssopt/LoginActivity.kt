package com.example.letssopt

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import kotlin.jvm.java
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.foundation.text.KeyboardOptions

class LoginActivity : ComponentActivity() {
    private var registeredEmail by mutableStateOf("")
    private var registeredPassword by mutableStateOf("")
    private val registerLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ){ result ->
        if(result.resultCode == RESULT_OK){
            registeredEmail = result.data?.getStringExtra("email") ?: ""
            registeredPassword = result.data?.getStringExtra("password") ?: ""
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LETSSOPTTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding),
                        launcher = registerLauncher,
                        registeredEmail = registeredEmail,
                        registeredPassword = registeredPassword,
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(
    name: String,
    modifier: Modifier = Modifier,
    launcher: androidx.activity.result.ActivityResultLauncher<Intent>? = null,
    registeredEmail: String = "",
    registeredPassword: String = ""
) {

    val context = LocalContext.current
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val allFilled = email.isNotEmpty() && password.isNotEmpty()


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
                text = "이메일로 로그인",
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
                    unfocusedTextColor = Color(0xFF666666),
                    focusedTextColor = Color(0xFF666666),
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
                    unfocusedTextColor = Color(0xFF666666),
                    focusedTextColor = Color(0xFF666666),
                    unfocusedContainerColor = Color(0xFF2A2A2A),
                    focusedContainerColor = Color(0xFF2A2A2A),
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent
                )
            )
        }

        Spacer(modifier = Modifier.height(334.dp))

        Text(
            text = "아직 계정이 없으신가요?  회원가입",
            modifier = Modifier
                .fillMaxWidth()
                .clickable() {
                    val intent = Intent(context,NextActivity::class.java)
                    launcher?.launch(intent)
                },
            color = Color(0xFF999999),
            fontSize = 14.sp,
            fontFamily = FontFamily(Font(R.font.pretendard_regular)),
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                if (email == registeredEmail && password == registeredPassword){
                    Toast.makeText(context,"로그인에 성공했습니다.", Toast.LENGTH_SHORT).show()
                    val intent = Intent(context, MainActivity2::class.java)
                    context.startActivity(intent)
                }
                else {
                    Toast.makeText(context,"이메일 또는 비밀번호가 올바르지 않습니다.", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            enabled = allFilled,
            shape = RoundedCornerShape(size = 8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFE8003C),
                disabledContainerColor = Color(0xFF555555)
            )
        ) {
            Text(
                text = "로그인",
                color = Color(0xFFFFFFFF),
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.pretendard_bold)),
                fontWeight = FontWeight.Bold
            )

        }

    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LETSSOPTTheme {
        Greeting("Android")
    }
}