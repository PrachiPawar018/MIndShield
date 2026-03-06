package com.example.mindpulse.data.repository

import com.example.mindpulse.domain.model.EmergencyAlert
import com.example.mindpulse.domain.model.EmergencyContact
import com.example.mindpulse.domain.model.JournalEntry
import com.example.mindpulse.domain.repository.DatabaseRepository
import com.example.mindpulse.utils.Resource
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class DatabaseRepositoryImpl(
    private val firestore: FirebaseFirestore
) : DatabaseRepository {

    override fun saveJournalEntry(entry: JournalEntry): Flow<Resource<Boolean>> = callbackFlow {
        trySend(Resource.Loading())
        val docRef = if (entry.entryId.isEmpty()) {
            firestore.collection("journalEntries").document()
        } else {
            firestore.collection("journalEntries").document(entry.entryId)
        }
        
        val newEntry = entry.copy(entryId = docRef.id)
        
        docRef.set(newEntry)
            .addOnSuccessListener {
                trySend(Resource.Success(true))
            }
            .addOnFailureListener {
                trySend(Resource.Error(it.message ?: "Failed to save journal entry"))
            }
        awaitClose()
    }

    override fun getJournalEntries(userId: String): Flow<Resource<List<JournalEntry>>> = callbackFlow {
        trySend(Resource.Loading())
        val subscription = firestore.collection("journalEntries")
            .whereEqualTo("userId", userId)
            .orderBy("createdAt", Query.Direction.DESCENDING)
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    trySend(Resource.Error(error.message ?: "Failed to fetch journal entries"))
                    return@addSnapshotListener
                }
                val entries = snapshot?.toObjects(JournalEntry::class.java) ?: emptyList()
                trySend(Resource.Success(entries))
            }
        awaitClose { subscription.remove() }
    }

    override fun addEmergencyContact(contact: EmergencyContact): Flow<Resource<Boolean>> = callbackFlow {
        trySend(Resource.Loading())
        val docRef = if (contact.contactId.isEmpty()) {
            firestore.collection("emergencyContacts").document()
        } else {
            firestore.collection("emergencyContacts").document(contact.contactId)
        }
        
        val newContact = contact.copy(contactId = docRef.id)
        
        docRef.set(newContact)
            .addOnSuccessListener {
                trySend(Resource.Success(true))
            }
            .addOnFailureListener {
                trySend(Resource.Error(it.message ?: "Failed to add emergency contact"))
            }
        awaitClose()
    }

    override fun getEmergencyContacts(userId: String): Flow<Resource<List<EmergencyContact>>> = callbackFlow {
        trySend(Resource.Loading())
        val subscription = firestore.collection("emergencyContacts")
            .whereEqualTo("userId", userId)
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    trySend(Resource.Error(error.message ?: "Failed to fetch emergency contacts"))
                    return@addSnapshotListener
                }
                val contacts = snapshot?.toObjects(EmergencyContact::class.java) ?: emptyList()
                trySend(Resource.Success(contacts))
            }
        awaitClose { subscription.remove() }
    }

    override fun deleteEmergencyContact(contactId: String): Flow<Resource<Boolean>> = callbackFlow {
        trySend(Resource.Loading())
        firestore.collection("emergencyContacts").document(contactId).delete()
            .addOnSuccessListener {
                trySend(Resource.Success(true))
            }
            .addOnFailureListener {
                trySend(Resource.Error(it.message ?: "Failed to delete contact"))
            }
        awaitClose()
    }

    override fun sendEmergencyAlert(alert: EmergencyAlert): Flow<Resource<Boolean>> = callbackFlow {
        trySend(Resource.Loading())
        val docRef = firestore.collection("emergencyAlerts").document()
        val newAlert = alert.copy(alertId = docRef.id)
        
        docRef.set(newAlert)
            .addOnSuccessListener {
                trySend(Resource.Success(true))
            }
            .addOnFailureListener {
                trySend(Resource.Error(it.message ?: "Failed to send alert"))
            }
        awaitClose()
    }

    override fun getEmergencyAlerts(userId: String): Flow<Resource<List<EmergencyAlert>>> = callbackFlow {
        trySend(Resource.Loading())
        val subscription = firestore.collection("emergencyAlerts")
            .whereEqualTo("userId", userId)
            .orderBy("createdAt", Query.Direction.DESCENDING)
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    trySend(Resource.Error(error.message ?: "Failed to fetch alerts"))
                    return@addSnapshotListener
                }
                val alerts = snapshot?.toObjects(EmergencyAlert::class.java) ?: emptyList()
                trySend(Resource.Success(alerts))
            }
        awaitClose { subscription.remove() }
    }
}
