package com.bugayov.educationorganizationapp.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.Date

@Parcelize
data class Deadline(
    val id: Int,
    val deadlineDate: Date,
    val typeOfWork: String,
    val subjectId: Int,
    val groupName: String?
) : Parcelable
