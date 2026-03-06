package com.example.mindpulse.domain.repository

import com.example.mindpulse.domain.model.EmergencyAlert
import com.example.mindpulse.domain.model.EmergencyContact
import com.example.mindpulse.domain.model.JournalEntry
import com.example.mindpulse.utils.Resource
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
