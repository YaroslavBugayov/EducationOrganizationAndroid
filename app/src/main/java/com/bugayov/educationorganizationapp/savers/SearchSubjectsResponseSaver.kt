package com.bugayov.educationorganizationapp.savers

import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.SaverScope
import com.bugayov.educationorganizationapp.models.SearchSubjectsResponse
import com.bugayov.educationorganizationapp.models.Subject

object SearchSubjectsResponseSaver : Saver<SearchSubjectsResponse, Map<String, Any?>> {
    override fun restore(value: Map<String, Any?>): SearchSubjectsResponse {
        val subjects: List<Subject> = value["subjects"] as? List<Subject> ?: emptyList()
        return SearchSubjectsResponse(subjects)
    }

    override fun SaverScope.save(value: SearchSubjectsResponse): Map<String, Any?> {
        return mapOf("subjects" to value.subjects)
    }
}