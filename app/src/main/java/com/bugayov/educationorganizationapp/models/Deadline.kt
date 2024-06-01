package com.bugayov.educationorganizationapp.models

import java.util.Date

data class Deadline(
    val id: Int,
    val deadlineDate: Date,
    val subjectId: Int,
    val groupName: String?
)
