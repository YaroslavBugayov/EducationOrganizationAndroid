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
import com.bugayov.educationorganizationapp.models.Deadline
import com.bugayov.educationorganizationapp.models.Rso
import java.text.SimpleDateFormat

@Composable
fun DeadlinesScreen(deadlines: List<Deadline>) {
    Column(modifier = Modifier) {
        if (deadlines.isNotEmpty()) {
            deadlines.forEach {
                DeadlineCard(it)
            }
        }
    }
}

@Composable
fun DeadlineCard(deadline: Deadline) {
    OutlinedCard(modifier = Modifier
        .fillMaxWidth()
        .padding(bottom = 10.dp)
    ) {
        Row {
            Column(modifier = Modifier.weight(1F)){
                Text(
                    text = deadline.typeOfWork,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(top = 10.dp, start = 10.dp, end = 10.dp)
                )
                Text(
                    text = SimpleDateFormat("dd:MM:yyyy").format(deadline.deadlineDate),
                    fontSize = 20.sp,
                    modifier = Modifier.padding(start = 10.dp, end = 10.dp)
                )
                Text(
                    text = deadline.groupName ?: "For everyone",
                    fontSize = 20.sp,
                    modifier = Modifier.padding(start = 10.dp, end = 10.dp, bottom = 10.dp)
                )
            }
        }
    }
}