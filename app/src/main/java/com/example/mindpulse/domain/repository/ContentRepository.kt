package com.example.mindpulse.domain.repository

import com.example.mindpulse.domain.model.Meditation
import com.example.mindpulse.utils.Resource
import kotlinx.coroutines.flow.Flow

interface ContentRepository {
    fun getRecommendedMeditations(): Flow<Resource<List<Meditation>>>
    fun getAllMeditations(): Flow<Resource<List<Meditation>>>
    fun getMeditationDetails(id: String): Flow<Resource<Meditation>>
}