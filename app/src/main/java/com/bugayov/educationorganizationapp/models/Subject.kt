package com.bugayov.educationorganizationapp.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Subject(
    val id: Int,
    val name: String,
    val teacherName: String
) : Parcelable
