package com.bugayov.educationorganizationapp.ui.components

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bugayov.educationorganizationapp.models.SubjectModel

@Composable
fun SubjectsList(modifier: Modifier = Modifier, subjectModels: List<SubjectModel>) {
    Column(modifier = modifier.padding(10.dp).fillMaxWidth().verticalScroll(state = ScrollState(0))) {
        for (i in 0..subjectModels.size) {
            OutlinedCard(modifier = Modifier.fillMaxWidth().padding(bottom = 10.dp)) {
                Text(
                    text = subjectModels[i].name,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(start = 10.dp, top = 10.dp, end = 10.dp)
                )
                Text(
                    text = subjectModels[i].teacher,
                    modifier = Modifier.padding(start = 10.dp, bottom = 10.dp, end = 10.dp)
                )
            }
        }

    }
}