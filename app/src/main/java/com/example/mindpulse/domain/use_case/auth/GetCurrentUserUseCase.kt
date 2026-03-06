package com.example.mindpulse.domain.use_case.auth

import com.example.mindpulse.domain.repository.AuthRepository
import com.example.mindpulse.domain.model.User

class GetCurrentUserUseCase(private val repository: AuthRepository) {
    operator fun invoke(): User? = repository.getCurrentUser()
}