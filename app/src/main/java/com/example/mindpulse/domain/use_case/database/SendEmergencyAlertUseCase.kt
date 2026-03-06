package com.example.mindpulse.domain.use_case.database

import com.example.mindpulse.domain.model.EmergencyAlert
import com.example.mindpulse.domain.repository.DatabaseRepository
import com.example.mindpulse.utils.Resource
import kotlinx.coroutines.flow.Flow

class SendEmergencyAlertUseCase(
    private val repository: DatabaseRepository
) {
    operator fun invoke(alert: EmergencyAlert): Flow<Resource<Boolean>> {
        return repository.sendEmergencyAlert(alert)
    }
}
