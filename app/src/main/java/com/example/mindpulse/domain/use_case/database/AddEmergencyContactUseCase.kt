package com.example.mindpulse.domain.use_case.database

import com.example.mindpulse.domain.model.EmergencyContact
import com.example.mindpulse.domain.repository.DatabaseRepository
import com.example.mindpulse.utils.Resource
import kotlinx.coroutines.flow.Flow

class AddEmergencyContactUseCase(
    private val repository: DatabaseRepository
) {
    operator fun invoke(contact: EmergencyContact): Flow<Resource<Boolean>> {
        return repository.addEmergencyContact(contact)
    }
}
