package com.example.mindpulse.domain.use_case.auth

import com.example.mindpulse.domain.repository.AuthRepository

class LogoutUseCase(private val repository: AuthRepository) {
    operator fun invoke() = repository.logout()
}