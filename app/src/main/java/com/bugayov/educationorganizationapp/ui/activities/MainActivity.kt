package com.bugayov.educationorganizationapp.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bugayov.educationorganizationapp.R
import com.bugayov.educationorganizationapp.ui.theme.EducationOrganizationAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MainScreen()
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun MainScreen() {
    val context = LocalContext.current
    val uriHandler = LocalUriHandler.current

    EducationOrganizationAppTheme {
        Column(
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth().weight(1F)
            ) {
                Button(onClick = {
                    context.startActivity(Intent(context, StudentActivity::class.java))
                }) {
                    Text(
                        text = "I am a student",
                        fontSize = 20.sp
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                Button(onClick = {
                    context.startActivity(Intent(context, TeacherActivity::class.java))
                }) {
                    Text(
                        text = "I am a teacher",
                        fontSize = 20.sp
                    )
                }
            }
            Image(
                modifier = Modifier.fillMaxWidth().clickable {
                    uriHandler.openUri("https://send.monobank.ua/jar/4Dyucs5PBU")
                },
                painter = painterResource(id = R.drawable.povernys_zhyvym),
                contentDescription = "mono",
            )
        }
    }
}