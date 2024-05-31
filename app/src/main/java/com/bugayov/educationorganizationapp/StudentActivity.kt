package com.bugayov.educationorganizationapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bugayov.educationorganizationapp.models.SubjectModel
import com.bugayov.educationorganizationapp.retrofit.RetrofitInstance
import com.bugayov.educationorganizationapp.ui.components.AddSubjectButton
import com.bugayov.educationorganizationapp.ui.components.NavigationMenu
import com.bugayov.educationorganizationapp.ui.components.SubjectsList
import com.bugayov.educationorganizationapp.ui.theme.EducationOrganizationAppTheme
import kotlinx.coroutines.launch

class StudentActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppNavigation()
        }

        lifecycleScope.launch {
            try {
                val subject = RetrofitInstance.api.getSubjectById(10)
                Log.d("Subject", subject.subject.name)
            } catch (e: Exception) {
                Log.d("Subject", "Problem")
            }
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "subjectsListScreen") {
        composable("findSubjectScreen") {
            FindSubjectScreen(navController)
        }
        composable("subjectsListScreen") {
            SubjectsListScreen(navController, emptyList())
        }
    }
}

@Composable
fun SubjectsListScreen(navController: NavHostController, subjectModels: List<SubjectModel>) {
    val context = LocalContext.current
    EducationOrganizationAppTheme {
        Column {
            NavigationMenu("My subjects", onClick = {
                context.startActivity(Intent(context, MainActivity::class.java))
            })
            SubjectsList(Modifier.weight(1F), subjectModels)
            AddSubjectButton(onClick = {
                navController.navigate("findSubjectScreen")
            })
        }
    }
}

@Composable
fun FindSubjectScreen(navController: NavHostController) {
    EducationOrganizationAppTheme {
        Column {
            NavigationMenu("Find subject", onClick = {
                navController.navigate("subjectsListScreen")
            })
        }
    }
}