package com.example.mindshield.domain.use_case.auth

import com.example.mindshield.domain.repository.AuthRepository
import com.example.mindshield.utils.Resource
import kotlinx.coroutines.flow.Flow

class SendEmailVerificationUseCase(private val repository: AuthRepository) {
    operator fun invoke(): Flow<Resource<Boolean>> = repository.sendEmailVerification()
}