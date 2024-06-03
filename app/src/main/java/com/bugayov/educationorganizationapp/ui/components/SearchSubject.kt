package com.bugayov.educationorganizationapp.ui.components

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.bugayov.educationorganizationapp.retrofit.RetrofitInstance
import com.bugayov.educationorganizationapp.models.PostText
import com.bugayov.educationorganizationapp.models.SearchSubjectsResponse
import com.bugayov.educationorganizationapp.storage.SubjectDao
import com.bugayov.educationorganizationapp.savers.SearchSubjectsResponseSaver
import kotlinx.coroutines.launch

@Composable
fun SearchSubject(modifier: Modifier = Modifier, subjectDao: SubjectDao, navController: NavHostController) {
    var search: String by rememberSaveable {
        mutableStateOf("")
    }
    val scope = rememberCoroutineScope()

    var subjects: SearchSubjectsResponse by rememberSaveable(
        stateSaver = SearchSubjectsResponseSaver
    ) {
        mutableStateOf(SearchSubjectsResponse(listOf()))
    }


    Column(modifier = modifier) {
        Row(modifier = Modifier.fillMaxWidth()) {
            OutlinedTextField(
                value = search,
                onValueChange = { search = it },
                modifier = Modifier
                    .weight(1F)
                    .padding(10.dp)
            )
            IconButton(onClick = {
                scope.launch {
                    try {
                        subjects = RetrofitInstance.api.searchSubjects(
                            PostText(search)
                        )
                    } catch (e: Exception) {
                        Log.d("Subject", e.message.toString())
                    }
                }
            }) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search"
                )
            }
        }
        SubjectsList(Modifier, subjects, subjectDao, CardButtonState.ADD, navController)
    }
}