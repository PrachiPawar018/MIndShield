package com.example.mindshield.domain.model

data class User(
    val id: String = "",
    val fullName: String = "",
    val email: String = "",
    val profileImageUrl: String? = null,
    val isEmailVerified: Boolean = false,
    val createdAt: Long = System.currentTimeMillis()
)