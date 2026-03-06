package com.example.mindpulse.domain.use_case.auth

import com.example.mindpulse.domain.repository.AuthRepository
import com.example.mindpulse.utils.Resource
import kotlinx.coroutines.flow.Flow

class SendEmailVerificationUseCase(private val repository: AuthRepository) {
    operator fun invoke(): Flow<Resource<Boolean>> = repository.sendEmailVerification()
}