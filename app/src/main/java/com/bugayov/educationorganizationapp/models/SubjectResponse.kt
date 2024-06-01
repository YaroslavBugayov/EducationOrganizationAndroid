package com.bugayov.educationorganizationapp.models

data class SubjectResponse(
    val subject: Subject,
    val rsos: List<Rso>,
    val infos: List<Info>,
    val deadlines: List<Deadline>
)
