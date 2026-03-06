package com.example.mindpulse.domain.use_case.auth

import com.example.mindpulse.domain.repository.AuthRepository
import com.example.mindpulse.utils.Resource
import kotlinx.coroutines.flow.Flow

class UpdateProfileUseCase(private val repository: AuthRepository) {
    operator fun invoke(fullName: String, profileImageUrl: String?): Flow<Resource<Boolean>> =
        repository.updateProfile(fullName, profileImageUrl)
}