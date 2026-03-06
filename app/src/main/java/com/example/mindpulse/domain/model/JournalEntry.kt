package com.example.mindshield.domain.model

data class JournalEntry(
    val entryId: String = "",
    val userId: String = "",
    val moodScore: Int = 0,
    val journalText: String = "",
    val sentiment: String = "",
    val createdAt: Long = System.currentTimeMillis()
)
