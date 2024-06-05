package com.bugayov.educationorganizationapp.ui.screens

import android.content.Intent
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.bugayov.educationorganizationapp.ui.activities.MainActivity
import com.bugayov.educationorganizationapp.models.SearchSubjectsResponse
import com.bugayov.educationorganizationapp.models.Subject
import com.bugayov.educationorganizationapp.storage.SubjectDao
import com.bugayov.educationorganizationapp.ui.components.AddSubjectButton
import com.bugayov.educationorganizationapp.ui.components.CardButtonState
import com.bugayov.educationorganizationapp.ui.components.HeaderMenu
import com.bugayov.educationorganizationapp.ui.components.SubjectsList
import com.bugayov.educationorganizationapp.savers.SearchSubjectsResponseSaver
import com.bugayov.educationorganizationapp.ui.theme.EducationOrganizationAppTheme
import kotlinx.coroutines.launch

@Composable
fun SubjectsListScreen(navController: NavHostController, subjectDao: SubjectDao) {
    val context = LocalContext.current
    var subjects: SearchSubjectsResponse by rememberSaveable(
        stateSaver = SearchSubjectsResponseSaver
    ) {
        mutableStateOf(SearchSubjectsResponse(listOf()))
    }
    val scope = rememberCoroutineScope()
    LaunchedEffect(context) {
        subjects = SearchSubjectsResponse(subjectDao.getAllSubjects().map { el -> Subject(el.id, el.name, el.teacherName) })
    }

    EducationOrganizationAppTheme {
        Column {
            HeaderMenu("My subjects", onClick = {
                context.startActivity(Intent(context, MainActivity::class.java))
            })
            SubjectsList(
                Modifier.weight(1F), subjects, subjectDao, CardButtonState.REMOVE,
                removeCallback = {
                    scope.launch {
                        subjects = SearchSubjectsResponse(subjectDao.getAllSubjects().map { el -> Subject(el.id, el.name, el.teacherName) })
                    }
                },
                navController = navController
            )
            AddSubjectButton(onClick = {
                navController.navigate("findSubjectScreen")
            })
        }
    }
}