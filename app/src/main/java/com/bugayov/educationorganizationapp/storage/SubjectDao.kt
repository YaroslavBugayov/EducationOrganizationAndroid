package com.bugayov.educationorganizationapp.storage

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.bugayov.educationorganizationapp.models.Subject

@Dao
interface SubjectDao {
    @Insert
    suspend fun insert(subject: SubjectEntity)

    @Query("SELECT * FROM subjects")
    suspend fun getAllSubjects(): List<SubjectEntity>

    @Query("DELETE FROM subjects WHERE id = :id")
    suspend fun delete(id: Int)
}