package com.example.mindshield.domain.use_case.database

import com.example.mindshield.domain.model.JournalEntry
import com.example.mindshield.domain.repository.DatabaseRepository
import com.example.mindshield.utils.Resource
import kotlinx.coroutines.flow.Flow

class GetJournalEntriesUseCase(
    private val repository: DatabaseRepository
) {
    operator fun invoke(userId: String): Flow<Resource<List<JournalEntry>>> {
        return repository.getJournalEntries(userId)
    }
}
