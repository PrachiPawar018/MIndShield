package com.example.mindshield.domain.use_case.database

import com.example.mindshield.domain.model.EmergencyContact
import com.example.mindshield.domain.repository.DatabaseRepository
import com.example.mindshield.utils.Resource
import kotlinx.coroutines.flow.Flow

class GetEmergencyContactsUseCase(
    private val repository: DatabaseRepository
) {
    operator fun invoke(userId: String): Flow<Resource<List<EmergencyContact>>> {
        return repository.getEmergencyContacts(userId)
    }
}
