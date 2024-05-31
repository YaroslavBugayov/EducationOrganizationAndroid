package com.bugayov.educationorganizationapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bugayov.educationorganizationapp.models.SubjectModel
import com.bugayov.educationorganizationapp.ui.components.AddSubjectButton
import com.bugayov.educationorganizationapp.ui.components.NavigationMenu
import com.bugayov.educationorganizationapp.ui.components.SubjectsList
import com.bugayov.educationorganizationapp.ui.theme.EducationOrganizationAppTheme

class StudentActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SubjectsListScreen(ArrayList())
        }
    }
}

@Composable
fun SubjectsListScreen(subjectModels: List<SubjectModel>) {
    EducationOrganizationAppTheme {
        Column {
            NavigationMenu("My subjects")
            SubjectsList(Modifier.weight(1F), subjectModels)
            AddSubjectButton()
        }
    }
}