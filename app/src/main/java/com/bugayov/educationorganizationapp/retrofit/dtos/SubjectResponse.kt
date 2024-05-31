package com.bugayov.educationorganizationapp.retrofit.dtos

data class SubjectResponse(
    val subject: Subject,
    val teacherName: String,
    val rsos: List<Rso>,
    val infos: List<Info>,
    val deadlines: List<Deadline>
)
