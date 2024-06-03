package com.bugayov.educationorganizationapp.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SubjectResponse (
    val subject: Subject,
    val rsos: List<Rso>,
    val infos: List<Info>,
    val deadlines: List<Deadline>
) : Parcelable
