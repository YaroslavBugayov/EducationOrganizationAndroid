package com.bugayov.educationorganizationapp.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.bugayov.educationorganizationapp.storage.SubjectDao
import com.bugayov.educationorganizationapp.ui.components.HeaderMenu
import com.bugayov.educationorganizationapp.ui.components.SearchSubject
import com.bugayov.educationorganizationapp.ui.theme.EducationOrganizationAppTheme

@Composable
fun FindSubjectScreen(navController: NavHostController, subjectDao: SubjectDao) {
    EducationOrganizationAppTheme {
        Column {
            HeaderMenu("Find subject", onClick = {
                navController.navigate("subjectsListScreen")
            })
            SearchSubject(Modifier.weight(1F), subjectDao, navController)
        }
    }
}