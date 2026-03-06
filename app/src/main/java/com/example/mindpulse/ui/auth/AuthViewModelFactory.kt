package com.example.mindshield.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mindshield.data.repository.AuthRepositoryImpl
import com.example.mindshield.domain.use_case.auth.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class AuthViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AuthViewModel::class.java)) {
            val auth = FirebaseAuth.getInstance()
            val firestore = FirebaseFirestore.getInstance()
            val repository = AuthRepositoryImpl(auth, firestore)
            
            return AuthViewModel(
                LoginUseCase(repository),
                SignUpUseCase(repository),
                LogoutUseCase(repository),
                SendEmailVerificationUseCase(repository),
                ResetPasswordUseCase(repository),
                GetCurrentUserUseCase(repository)
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}