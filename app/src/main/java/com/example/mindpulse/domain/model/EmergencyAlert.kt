package com.example.mindshield.domain.model

data class EmergencyAlert(
    val alertId: String = "",
    val userId: String = "",
    val contactId: String = "",
    val message: String = "",
    val createdAt: Long = System.currentTimeMillis()
)
