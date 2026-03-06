package com.example.mindshield.domain.use_case.auth

import com.example.mindshield.domain.repository.AuthRepository
import com.example.mindshield.utils.Resource
import kotlinx.coroutines.flow.Flow

class UpdateProfileUseCase(private val repository: AuthRepository) {
    operator fun invoke(fullName: String, profileImageUrl: String?): Flow<Resource<Boolean>> =
        repository.updateProfile(fullName, profileImageUrl)
}