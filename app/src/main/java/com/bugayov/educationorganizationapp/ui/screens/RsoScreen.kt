package com.bugayov.educationorganizationapp.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bugayov.educationorganizationapp.models.Info
import com.bugayov.educationorganizationapp.models.Rso

@Composable
fun RsoScreen(rsos: List<Rso>) {
    Column(modifier = Modifier) {
        if (rsos.isNotEmpty()) {
            rsos.forEach {
                RsoCard(it)
            }
        }
    }
}

@Composable
fun RsoCard(rso: Rso) {
    OutlinedCard(modifier = Modifier
        .fillMaxWidth()
        .padding(bottom = 10.dp)
    ) {
        Row {
            Column(modifier = Modifier.weight(1F)){
                Text(
                    text = rso.typeOfWork,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(10.dp)
                )
            }
        }
    }
}