package com.bugayov.educationorganizationapp.controllers

import com.bugayov.educationorganizationapp.BuildConfig
import com.bugayov.educationorganizationapp.retrofit.apis.SubjectApi
import com.bugayov.educationorganizationapp.retrofit.dtos.SubjectResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SubjectController(private val subjectApi: SubjectApi) {
    suspend fun getSubject(id: Int): SubjectResponse {
        return withContext(Dispatchers.IO) {
            subjectApi.getSubjectById(id)
        }
    }
}