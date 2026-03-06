package com.example.mindpulse.domain.model

data class EmergencyContact(
    val contactId: String = "",
    val userId: String = "",
    val name: String = "",
    val email: String = "",
    val phone: String = "",
    val relation: String = "",
    val verified: Boolean = false
)
