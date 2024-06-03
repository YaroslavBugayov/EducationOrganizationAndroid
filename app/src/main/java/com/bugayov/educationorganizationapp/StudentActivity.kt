package com.bugayov.educationorganizationapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.bugayov.educationorganizationapp.retrofit.RetrofitInstance
import com.bugayov.educationorganizationapp.storage.SubjectDao
import com.bugayov.educationorganizationapp.storage.SubjectsDatabase
import com.bugayov.educationorganizationapp.ui.screens.FindSubjectScreen
import com.bugayov.educationorganizationapp.ui.screens.SubjectScreen
import com.bugayov.educationorganizationapp.ui.screens.SubjectsListScreen

class StudentActivity : ComponentActivity() {
    private lateinit var db: SubjectsDatabase
    private lateinit var subjectDao: SubjectDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        db = SubjectsDatabase.getDatabase(this)
        subjectDao = db.subjectDao()
        enableEdgeToEdge()
        setContent {
            AppNavigation(subjectDao)
        }
    }
}

@Composable
fun AppNavigation(subjectDao: SubjectDao) {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "subjectsListScreen") {
        composable("findSubjectScreen") {
            FindSubjectScreen(navController, subjectDao)
        }
        composable("subjectsListScreen") {
            SubjectsListScreen(navController, subjectDao)
        }
        composable(
            "subjectScreen/{subjectId}",
            arguments = listOf(navArgument("subjectId") { type = NavType.IntType })
        ) { backStackEntry ->
            val subjectId = backStackEntry.arguments?.getInt("subjectId") ?: return@composable
            SubjectScreen(navController, subjectId)
        }
    }
}