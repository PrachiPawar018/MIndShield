package com.example.mindshield.domain.use_case.database

import com.example.mindshield.domain.model.EmergencyContact
import com.example.mindshield.domain.repository.DatabaseRepository
import com.example.mindshield.utils.Resource
import kotlinx.coroutines.flow.Flow

class AddEmergencyContactUseCase(
    private val repository: DatabaseRepository
) {
    operator fun invoke(contact: EmergencyContact): Flow<Resource<Boolean>> {
        return repository.addEmergencyContact(contact)
    }
}
