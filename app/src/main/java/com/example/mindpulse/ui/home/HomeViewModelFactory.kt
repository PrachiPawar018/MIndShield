package com.example.mindpulse.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mindpulse.data.repository.ContentRepositoryImpl
import com.example.mindpulse.domain.use_case.content.GetRecommendedMeditationsUseCase
import com.google.firebase.firestore.FirebaseFirestore

class HomeViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            val firestore = FirebaseFirestore.getInstance()
            val repository = ContentRepositoryImpl(firestore)
            return HomeViewModel(GetRecommendedMeditationsUseCase(repository)) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}