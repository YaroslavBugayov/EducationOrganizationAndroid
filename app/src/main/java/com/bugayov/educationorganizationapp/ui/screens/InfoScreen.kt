package com.bugayov.educationorganizationapp.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bugayov.educationorganizationapp.models.Info

@Composable
fun InfoScreen(infos: List<Info>) {
    Column(modifier = Modifier) {
        if (infos.isNotEmpty()) {
            infos.forEach {
                InfoCard(it)
            }
        }
    }
}

@Composable
fun InfoCard(info: Info) {
    OutlinedCard(modifier = Modifier
        .fillMaxWidth()
        .padding(bottom = 10.dp)
    ) {
        Row {
            Column(modifier = Modifier.weight(1F)){
                Text(
                    text = info.text,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(10.dp)
                )
            }
        }
    }
}