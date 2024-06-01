package com.bugayov.educationorganizationapp.storage

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.bugayov.educationorganizationapp.models.Subject

@Database(entities = [SubjectEntity::class], version = 1, exportSchema = false)
abstract class SubjectsDatabase: RoomDatabase() {
    abstract fun subjectDao(): SubjectDao

    companion object {
        @Volatile
        private var INSTANCE: SubjectsDatabase? = null

        fun getDatabase(context: Context): SubjectsDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SubjectsDatabase::class.java,
                    "subject_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}