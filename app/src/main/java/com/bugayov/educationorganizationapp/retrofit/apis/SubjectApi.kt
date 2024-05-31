package com.bugayov.educationorganizationapp.retrofit.apis

import com.bugayov.educationorganizationapp.retrofit.dtos.SubjectResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface SubjectApi {
    @GET("subject/get/{id}")
    suspend fun getSubjectById(@Path("id") id: Int): SubjectResponse
}