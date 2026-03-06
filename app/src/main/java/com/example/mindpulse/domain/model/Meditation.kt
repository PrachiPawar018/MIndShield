package com.example.mindpulse.domain.model

data class Meditation(
    val id: String = "",
    val title: String = "",
    val category: String = "",
    val duration: String = "",
    val description: String = "",
    val imageUrl: String? = null
)