package com.example.mindshield.domain.use_case.auth

import com.example.mindshield.domain.repository.AuthRepository
import com.example.mindshield.utils.Resource
import kotlinx.coroutines.flow.Flow

class SignUpUseCase(private val repository: AuthRepository) {
    operator fun invoke(fullName: String, email: String, password: String): Flow<Resource<Boolean>> {
        return repository.signUp(fullName, email, password)
    }
}