package com.bugayov.educationorganizationapp.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bugayov.educationorganizationapp.ui.components.HeaderMenu
import com.bugayov.educationorganizationapp.ui.theme.EducationOrganizationAppTheme

class TeacherActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LoginScreen()
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
    )
@Composable
fun LoginScreen() {
    var login: String by rememberSaveable { mutableStateOf("") }
    var password: String by rememberSaveable { mutableStateOf("") }
    val context = LocalContext.current

    EducationOrganizationAppTheme {
        HeaderMenu("Login", onClick = {
            context.startActivity(Intent(context, MainActivity::class.java))
        })
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(60.dp)
        ) {
            Text(
                text = "Login:",
                fontSize = 20.sp,
                modifier = Modifier.align(Alignment.Start)
            )
            OutlinedTextField(value = login, onValueChange = { login = it })
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Password:",
                fontSize = 20.sp,
                modifier = Modifier.align(Alignment.Start)
            )
            OutlinedTextField(value = password, onValueChange = { password = it })
            Spacer(modifier = Modifier.height(10.dp))
            Button(onClick = { /*TODO*/ }) {
                Text(
                    text = "Sign In",
                    fontSize = 20.sp,
                )
            }
        }
    }
}
