package com.bugayov.educationorganizationapp.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Rso(
    val id: Int,
    val typeOfWork: String,
    val subjectId: Int
) : Parcelable
