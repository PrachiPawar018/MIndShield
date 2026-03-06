package com.example.mindpulse.domain.use_case.content

import com.example.mindpulse.domain.repository.ContentRepository

class GetRecommendedMeditationsUseCase(private val repository: ContentRepository) {
    operator fun invoke() = repository.getRecommendedMeditations()
}