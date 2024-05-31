package com.bugayov.educationorganizationapp.retrofit

import com.bugayov.educationorganizationapp.BuildConfig
import com.bugayov.educationorganizationapp.retrofit.apis.SubjectApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BuildConfig.API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: SubjectApi by lazy {
        retrofit.create(SubjectApi::class.java)
    }
}