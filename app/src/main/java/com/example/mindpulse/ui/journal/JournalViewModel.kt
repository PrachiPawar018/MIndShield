package com.example.mindpulse.ui.journal

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mindpulse.domain.model.JournalEntry
import com.example.mindpulse.domain.use_case.database.GetJournalEntriesUseCase
import com.example.mindpulse.domain.use_case.database.SaveJournalEntryUseCase
import com.example.mindpulse.utils.Resource
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class JournalViewModel(
    private val saveJournalEntryUseCase: SaveJournalEntryUseCase,
    private val getJournalEntriesUseCase: GetJournalEntriesUseCase
) : ViewModel() {

    private val _saveState = MutableLiveData<Resource<Boolean>>()
    val saveState: LiveData<Resource<Boolean>> = _saveState

    private val _entries = MutableLiveData<Resource<List<JournalEntry>>>()
    val entries: LiveData<Resource<List<JournalEntry>>> = _entries

    fun saveEntry(userId: String, moodScore: Int, journalText: String, sentiment: String) {
        val entry = JournalEntry(
            userId = userId,
            moodScore = moodScore,
            journalText = journalText,
            sentiment = sentiment
        )
        saveJournalEntryUseCase(entry).onEach { result ->
            _saveState.value = result
        }.launchIn(viewModelScope)
    }

    fun getEntries(userId: String) {
        getJournalEntriesUseCase(userId).onEach { result ->
            _entries.value = result
        }.launchIn(viewModelScope)
    }
}
