package com.example.mindpulse.domain.use_case.database

import com.example.mindpulse.domain.model.JournalEntry
import com.example.mindpulse.domain.repository.DatabaseRepository
import com.example.mindpulse.utils.Resource
import kotlinx.coroutines.flow.Flow

class SaveJournalEntryUseCase(
    private val repository: DatabaseRepository
) {
    operator fun invoke(entry: JournalEntry): Flow<Resource<Boolean>> {
        return repository.saveJournalEntry(entry)
    }
}
