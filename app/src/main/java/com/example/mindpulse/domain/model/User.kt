package com.example.mindpulse.domain.model

data class User(
    val id: String = "",
    val fullName: String = "",
    val email: String = "",
    val profileImageUrl: String? = null,
    val isEmailVerified: Boolean = false
)