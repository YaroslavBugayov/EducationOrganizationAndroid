package com.bugayov.educationorganizationapp.ui.screens

import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bugayov.educationorganizationapp.models.Subject
import com.bugayov.educationorganizationapp.models.SubjectResponse
import com.bugayov.educationorganizationapp.retrofit.RetrofitInstance
import com.bugayov.educationorganizationapp.ui.components.HeaderMenu
import com.bugayov.educationorganizationapp.ui.theme.EducationOrganizationAppTheme
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

@Composable
fun SubjectScreen(navController: NavHostController, subjectId: Int) {
    val context = LocalContext.current
    val subjectNavController = rememberNavController()

    var subjectResponse: SubjectResponse by rememberSaveable {
        mutableStateOf(SubjectResponse(Subject(0, "Loading...", "Loading..."), listOf(), listOf(), listOf()))
    }

    LaunchedEffect(context) {
        try {
            subjectResponse = RetrofitInstance.api.getSubjectById(subjectId)
        } catch (e: Exception) {
            Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
        }
    }
    EducationOrganizationAppTheme {
        Column {
            HeaderMenu(
                title = subjectResponse.subject.name,
                onClick = {
                    navController.navigate("subjectsListScreen")
                }
            )
            SubjectNavHost(
                navController = subjectNavController,
                modifier = Modifier.weight(1F).padding(10.dp).verticalScroll(state = ScrollState(0)),
                subject = subjectResponse)
            BottomNavBar(navController = subjectNavController)
        }
    }
}

@Composable
fun SubjectNavHost(navController: NavHostController, modifier: Modifier, subject: SubjectResponse) {
    NavHost(navController = navController, startDestination = "rso", modifier = modifier) {
        composable("rso") {
            RsoScreen(subject.rsos)
        }
        composable("deadlines") {
            DeadlinesScreen(subject.deadlines)
        }
        composable("info") {
            InfoScreen(subject.infos)
        }
    }
}

@Composable
fun BottomNavBar(navController: NavController) {
    var selectedTabIndex by rememberSaveable {
        mutableStateOf(0)
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        BottomNavItem(
            icon = Icons.Default.List,
            label = "RSO",
            isSelected = selectedTabIndex == 0,
            onClick = {
                selectedTabIndex = 0
                navController.navigate("rso")
            }
        )
        BottomNavItem(
            icon = Icons.Default.DateRange,
            label = "Deadlines",
            isSelected = selectedTabIndex == 1,
            onClick = {
                selectedTabIndex = 1
                navController.navigate("deadlines")
            }
        )
        BottomNavItem(
            icon = Icons.Default.Info,
            label = "Info",
            isSelected = selectedTabIndex == 2,
            onClick = {
                selectedTabIndex = 2
                navController.navigate("info")
            }
        )
    }
}

@Composable
fun BottomNavItem(
    icon: ImageVector,
    label: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val modifier = if (isSelected) Modifier.border(1.dp, Color.Black, CircleShape) else Modifier
    Column(
        modifier = modifier
            .padding(10.dp)
            .clickable(onClick = onClick),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(imageVector = icon, contentDescription = label)
        Text(text = label)
    }
}