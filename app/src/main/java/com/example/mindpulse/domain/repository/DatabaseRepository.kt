package com.example.mindshield.domain.repository

import com.example.mindshield.domain.model.EmergencyAlert
import com.example.mindshield.domain.model.EmergencyContact
import com.example.mindshield.domain.model.JournalEntry
import com.example.mindshield.utils.Resource
import kotlinx.coroutines.flow.Flow

interface DatabaseRepository {
    // Journal Entries
    fun saveJournalEntry(entry: JournalEntry): Flow<Resource<Boolean>>
    fun getJournalEntries(userId: String): Flow<Resource<List<JournalEntry>>>

    // Emergency Contacts
    fun addEmergencyContact(contact: EmergencyContact): Flow<Resource<Boolean>>
    fun getEmergencyContacts(userId: String): Flow<Resource<List<EmergencyContact>>>
    fun deleteEmergencyContact(contactId: String): Flow<Resource<Boolean>>

    // Emergency Alerts
    fun sendEmergencyAlert(alert: EmergencyAlert): Flow<Resource<Boolean>>
    fun getEmergencyAlerts(userId: String): Flow<Resource<List<EmergencyAlert>>>
}
