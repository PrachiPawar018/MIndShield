package com.example.mindshield.domain.use_case.auth

import com.example.mindshield.domain.repository.AuthRepository
import com.example.mindshield.utils.Resource
import kotlinx.coroutines.flow.Flow

class ResetPasswordUseCase(private val repository: AuthRepository) {
    operator fun invoke(email: String): Flow<Resource<Boolean>> = repository.resetPassword(email)
}