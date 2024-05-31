package com.bugayov.educationorganizationapp.ui.components

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
    Box(modifier = modifier.fillMaxWidth()) {
        if (subjectModels.isNotEmpty()) {
            LazyColumn(modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
                .verticalScroll(state = ScrollState(0))) {
                items(subjectModels) { item ->
                    OutlinedCard(modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 10.dp)) {
                        Text(
                            text = item.name,
                            fontSize = 20.sp,
                            modifier = Modifier.padding(start = 10.dp, top = 10.dp, end = 10.dp)
                        )
                        Text(
                            text = item.teacher,
                            modifier = Modifier.padding(start = 10.dp, bottom = 10.dp, end = 10.dp)
                        )
                    }
                }
            }
        }
    }
}