package com.example.mindshield.domain.repository

import com.example.mindshield.domain.model.User
import com.example.mindshield.utils.Resource
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    fun login(email: String, password: String): Flow<Resource<Boolean>>
    fun signUp(fullName: String, email: String, password: String): Flow<Resource<Boolean>>
    fun logout()
    fun getCurrentUser(): User?
    fun sendEmailVerification(): Flow<Resource<Boolean>>
    fun resetPassword(email: String): Flow<Resource<Boolean>>
    fun updateProfile(fullName: String, profileImageUrl: String?): Flow<Resource<Boolean>>
}