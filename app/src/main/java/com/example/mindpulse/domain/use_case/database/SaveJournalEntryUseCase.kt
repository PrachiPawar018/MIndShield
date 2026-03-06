package com.example.mindshield.domain.use_case.database

import com.example.mindshield.domain.model.JournalEntry
import com.example.mindshield.domain.repository.DatabaseRepository
import com.example.mindshield.utils.Resource
import kotlinx.coroutines.flow.Flow

class SaveJournalEntryUseCase(
    private val repository: DatabaseRepository
) {
    operator fun invoke(entry: JournalEntry): Flow<Resource<Boolean>> {
        return repository.saveJournalEntry(entry)
    }
}
