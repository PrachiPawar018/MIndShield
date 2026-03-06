package com.example.mindpulse.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mindpulse.domain.model.User
import com.example.mindpulse.domain.use_case.auth.*
import com.example.mindpulse.utils.Resource
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class AuthViewModel(
    private val loginUseCase: LoginUseCase,
    private val signUpUseCase: SignUpUseCase,
    private val logoutUseCase: LogoutUseCase,
    private val sendEmailVerificationUseCase: SendEmailVerificationUseCase,
    private val resetPasswordUseCase: ResetPasswordUseCase,
    private val getCurrentUserUseCase: GetCurrentUserUseCase
) : ViewModel() {

    private val _authState = MutableLiveData<Resource<Boolean>>()
    val authState: LiveData<Resource<Boolean>> = _authState

    private val _currentUser = MutableLiveData<User?>()
    val currentUser: LiveData<User?> = _currentUser

    init {
        _currentUser.value = getCurrentUserUseCase()
    }

    fun login(email: String, password: String) {
        loginUseCase(email, password).onEach { result ->
            _authState.value = result
            if (result is Resource.Success) {
                _currentUser.value = getCurrentUserUseCase()
            }
        }.launchIn(viewModelScope)
    }

    fun signUp(fullName: String, email: String, password: String) {
        signUpUseCase(fullName, email, password).onEach { result ->
            _authState.value = result
            if (result is Resource.Success) {
                _currentUser.value = getCurrentUserUseCase()
                sendEmailVerification()
            }
        }.launchIn(viewModelScope)
    }

    fun logout() {
        logoutUseCase()
        _currentUser.value = null
    }

    fun sendEmailVerification() {
        sendEmailVerificationUseCase().onEach { _ ->
            // Verification email sent
        }.launchIn(viewModelScope)
    }

    fun resetPassword(email: String) {
        resetPasswordUseCase(email).onEach { result ->
            _authState.value = result
        }.launchIn(viewModelScope)
    }
}