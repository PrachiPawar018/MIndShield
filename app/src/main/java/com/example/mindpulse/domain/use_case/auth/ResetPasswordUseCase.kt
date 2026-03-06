package com.example.mindpulse.domain.use_case.auth

import com.example.mindpulse.domain.repository.AuthRepository
import com.example.mindpulse.utils.Resource
import kotlinx.coroutines.flow.Flow

class ResetPasswordUseCase(private val repository: AuthRepository) {
    operator fun invoke(email: String): Flow<Resource<Boolean>> = repository.resetPassword(email)
}