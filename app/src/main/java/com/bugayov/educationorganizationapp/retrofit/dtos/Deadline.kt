package com.bugayov.educationorganizationapp.retrofit.dtos

import java.util.Date

data class Deadline(
    val id: Int,
    val deadlineDate: Date,
    val subjectId: Int,
    val groupName: String?
)
