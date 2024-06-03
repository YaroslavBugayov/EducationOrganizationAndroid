package com.bugayov.educationorganizationapp.ui.components

import android.util.Log
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.bugayov.educationorganizationapp.models.PostText
import com.bugayov.educationorganizationapp.models.SearchSubjectsResponse
import com.bugayov.educationorganizationapp.models.Subject
import com.bugayov.educationorganizationapp.retrofit.RetrofitInstance
import com.bugayov.educationorganizationapp.storage.SubjectDao
import com.bugayov.educationorganizationapp.storage.SubjectEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun SubjectsList(
    modifier: Modifier = Modifier,
    subjectModels: SearchSubjectsResponse,
    subjectDao: SubjectDao,
    buttonState: CardButtonState,
    navController: NavController,
    removeCallback: () -> Unit = {}
) {
    val scope = rememberCoroutineScope()

    Box(modifier = modifier.fillMaxWidth()) {
        if (subjectModels.subjects.isNotEmpty()) {
            Column(modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
                .verticalScroll(state = ScrollState(0))
            ) {
                subjectModels.subjects.forEach {
                    SubjectCard(it, scope, subjectDao, buttonState, navController, removeCallback)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SubjectCard(
    subject: Subject,
    scope: CoroutineScope,
    subjectDao: SubjectDao,
    buttonState: CardButtonState,
    navController: NavController,
    removeCallback: () -> Unit
) {
    OutlinedCard(modifier = Modifier
        .fillMaxWidth()
        .padding(bottom = 10.dp),
        onClick = {
            navController.navigate("subjectScreen/${subject.id}")
        }) {
        Row {
            Column(modifier = Modifier.weight(1F)){
                Text(
                    text = subject.name,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(start = 10.dp, top = 10.dp, end = 10.dp)
                )
                Text(
                    text = subject.teacherName,
                    modifier = Modifier.padding(start = 10.dp, bottom = 10.dp, end = 10.dp)
                )
            }
            if (buttonState == CardButtonState.ADD) {
                AddButton(subject, scope, subjectDao)
            } else {
                RemoveButton(subject, scope, subjectDao, removeCallback)
            }
        }
    }
}

@Composable
fun AddButton(subject: Subject, scope: CoroutineScope, subjectDao: SubjectDao) {
    IconButton(onClick = {
        scope.launch {
            subjectDao.insert(SubjectEntity(subject.id, subject.name, subject.teacherName))
        }
    }, Modifier.height(70.dp)) {
        Icon(imageVector = Icons.Default.Home, contentDescription = "Add to list")
    }
}

@Composable
fun RemoveButton(subject: Subject, scope: CoroutineScope, subjectDao: SubjectDao, removeCallback: () -> Unit) {
    IconButton(onClick = {
        scope.launch {
            subjectDao.delete(subject.id)
            removeCallback.invoke()
        }
    }, Modifier.height(70.dp)) {
        Icon(imageVector = Icons.Default.Close, contentDescription = "Remove from list")
    }
}