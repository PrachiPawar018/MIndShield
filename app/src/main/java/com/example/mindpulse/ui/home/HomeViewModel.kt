package com.example.mindpulse.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mindpulse.domain.model.Meditation
import com.example.mindpulse.domain.use_case.content.GetRecommendedMeditationsUseCase
import com.example.mindpulse.utils.Resource
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class HomeViewModel(
    private val getRecommendedMeditationsUseCase: GetRecommendedMeditationsUseCase
) : ViewModel() {

    private val _meditations = MutableLiveData<Resource<List<Meditation>>>()
    val meditations: LiveData<Resource<List<Meditation>>> = _meditations

    init {
        fetchRecommended()
    }

    private fun fetchRecommended() {
        getRecommendedMeditationsUseCase().onEach { result ->
            _meditations.value = result
        }.launchIn(viewModelScope)
    }
}