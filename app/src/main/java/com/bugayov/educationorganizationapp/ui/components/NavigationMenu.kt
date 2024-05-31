package com.bugayov.educationorganizationapp.ui.components

import android.content.Context
import android.content.Intent
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.bugayov.educationorganizationapp.MainActivity

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationMenu(title: String = "", onClick: () -> Unit) {
    TopAppBar(
        navigationIcon = {
            IconButton(
                onClick = onClick
            ) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
            }
        },
        title = {
            Text(
                text = title,
                fontSize = 20.sp
            )
        }
    )
}