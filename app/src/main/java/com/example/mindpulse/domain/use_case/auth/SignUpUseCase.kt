package com.example.mindpulse.domain.use_case.auth

import com.example.mindpulse.domain.repository.AuthRepository
import com.example.mindpulse.utils.Resource
import kotlinx.coroutines.flow.Flow

class SignUpUseCase(private val repository: AuthRepository) {
    operator fun invoke(fullName: String, email: String, password: String): Flow<Resource<Boolean>> {
        return repository.signUp(fullName, email, password)
    }
}