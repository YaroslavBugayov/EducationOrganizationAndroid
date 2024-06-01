package com.bugayov.educationorganizationapp.retrofit.apis

import com.bugayov.educationorganizationapp.models.PostText
import com.bugayov.educationorganizationapp.models.SearchSubjectsResponse
import com.bugayov.educationorganizationapp.models.SubjectResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface SubjectApi {
    @GET("subject/get/{id}")
    suspend fun getSubjectById(@Path("id") id: Int): SubjectResponse

    @POST("subject/search")
    suspend fun searchSubjects(@Body text: PostText): SearchSubjectsResponse
}