package com.example.mindshield.domain.use_case.auth

import com.example.mindshield.domain.repository.AuthRepository

class LogoutUseCase(private val repository: AuthRepository) {
    operator fun invoke() = repository.logout()
}