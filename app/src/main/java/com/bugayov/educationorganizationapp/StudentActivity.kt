package com.bugayov.educationorganizationapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.SaverScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bugayov.educationorganizationapp.retrofit.RetrofitInstance
import com.bugayov.educationorganizationapp.models.SearchSubjectsResponse
import com.bugayov.educationorganizationapp.models.Subject
import com.bugayov.educationorganizationapp.storage.SubjectDao
import com.bugayov.educationorganizationapp.storage.SubjectsDatabase
import com.bugayov.educationorganizationapp.ui.components.AddSubjectButton
import com.bugayov.educationorganizationapp.ui.components.CardButtonState
import com.bugayov.educationorganizationapp.ui.components.NavigationMenu
import com.bugayov.educationorganizationapp.ui.components.SearchSubject
import com.bugayov.educationorganizationapp.ui.components.SubjectsList
import com.bugayov.educationorganizationapp.ui.theme.EducationOrganizationAppTheme
import kotlinx.coroutines.launch

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
fun AppNavigation(subjectDao: SubjectDao) {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "subjectsListScreen") {
        composable("findSubjectScreen") {
            FindSubjectScreen(navController, subjectDao)
        }
        composable("subjectsListScreen") {
            SubjectsListScreen(navController, subjectDao)
        }
    }
}

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
            NavigationMenu("My subjects", onClick = {
                context.startActivity(Intent(context, MainActivity::class.java))
            })
            SubjectsList(Modifier.weight(1F), subjects, subjectDao, CardButtonState.REMOVE,
                removeCallback = {
                    scope.launch {
                        subjects = SearchSubjectsResponse(subjectDao.getAllSubjects().map { el -> Subject(el.id, el.name, el.teacherName) })
                    }
                })
            AddSubjectButton(onClick = {
                navController.navigate("findSubjectScreen")
            })
        }
    }
}

@Composable
fun FindSubjectScreen(navController: NavHostController, subjectDao: SubjectDao) {
    EducationOrganizationAppTheme {
        Column {
            NavigationMenu("Find subject", onClick = {
                navController.navigate("subjectsListScreen")
            })
            SearchSubject(Modifier.weight(1F), subjectDao)
        }
    }
}

object SearchSubjectsResponseSaver : Saver<SearchSubjectsResponse, Map<String, Any?>> {
    override fun restore(value: Map<String, Any?>): SearchSubjectsResponse {
        val subjects: List<Subject> = value["subjects"] as? List<Subject> ?: emptyList()
        return SearchSubjectsResponse(subjects)
    }

    override fun SaverScope.save(value: SearchSubjectsResponse): Map<String, Any?> {
        return mapOf("subjects" to value.subjects)
    }
}