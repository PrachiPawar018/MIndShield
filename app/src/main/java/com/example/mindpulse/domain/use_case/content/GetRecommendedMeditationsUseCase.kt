package com.example.mindshield.domain.use_case.content

import com.example.mindshield.domain.model.Meditation
import com.example.mindshield.domain.repository.ContentRepository
import com.example.mindshield.utils.Resource

class GetRecommendedMeditationsUseCase(private val repository: ContentRepository) {
    operator fun invoke() = repository.getRecommendedMeditations()
}