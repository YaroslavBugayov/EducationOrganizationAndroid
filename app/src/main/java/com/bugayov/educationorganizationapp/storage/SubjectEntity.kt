package com.bugayov.educationorganizationapp.storage

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "subjects")
data class SubjectEntity(
    @PrimaryKey(autoGenerate = false) val id: Int,
    val name: String,
    val teacherName: String
)
