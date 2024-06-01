package com.bugayov.educationorganizationapp.controllers

import com.bugayov.educationorganizationapp.retrofit.apis.SubjectApi
import com.bugayov.educationorganizationapp.models.SubjectResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SubjectController(private val subjectApi: SubjectApi) {
    suspend fun getSubject(id: Int): SubjectResponse {
        return withContext(Dispatchers.IO) {
            subjectApi.getSubjectById(id)
        }
    }
}