package com.example.mindshield.domain.use_case.auth

import com.example.mindshield.domain.repository.AuthRepository
import com.example.mindshield.domain.model.User

class GetCurrentUserUseCase(private val repository: AuthRepository) {
    operator fun invoke(): User? = repository.getCurrentUser()
}