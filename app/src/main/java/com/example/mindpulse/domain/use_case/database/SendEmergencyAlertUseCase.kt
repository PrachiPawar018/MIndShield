package com.example.mindshield.domain.use_case.database

import com.example.mindshield.domain.model.EmergencyAlert
import com.example.mindshield.domain.repository.DatabaseRepository
import com.example.mindshield.utils.Resource
import kotlinx.coroutines.flow.Flow

class SendEmergencyAlertUseCase(
    private val repository: DatabaseRepository
) {
    operator fun invoke(alert: EmergencyAlert): Flow<Resource<Boolean>> {
        return repository.sendEmergencyAlert(alert)
    }
}
