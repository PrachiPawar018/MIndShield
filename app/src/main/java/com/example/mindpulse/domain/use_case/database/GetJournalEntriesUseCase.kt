package com.example.mindpulse.domain.use_case.database

import com.example.mindpulse.domain.model.JournalEntry
import com.example.mindpulse.domain.repository.DatabaseRepository
import com.example.mindpulse.utils.Resource
import kotlinx.coroutines.flow.Flow

class GetJournalEntriesUseCase(
    private val repository: DatabaseRepository
) {
    operator fun invoke(userId: String): Flow<Resource<List<JournalEntry>>> {
        return repository.getJournalEntries(userId)
    }
}
