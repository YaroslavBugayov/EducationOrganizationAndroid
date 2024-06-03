package com.bugayov.educationorganizationapp.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Info(
    val id: Int,
    val text: String,
    val subjectId: Int
) : Parcelable
