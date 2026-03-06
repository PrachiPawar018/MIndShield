package com.example.mindpulse.ui.emergency

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mindpulse.domain.model.EmergencyAlert
import com.example.mindpulse.domain.model.EmergencyContact
import com.example.mindpulse.domain.use_case.database.AddEmergencyContactUseCase
import com.example.mindpulse.domain.use_case.database.GetEmergencyContactsUseCase
import com.example.mindpulse.domain.use_case.database.SendEmergencyAlertUseCase
import com.example.mindpulse.utils.Resource
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class EmergencyViewModel(
    private val addEmergencyContactUseCase: AddEmergencyContactUseCase,
    private val getEmergencyContactsUseCase: GetEmergencyContactsUseCase,
    private val sendEmergencyAlertUseCase: SendEmergencyAlertUseCase
) : ViewModel() {

    private val _contactState = MutableLiveData<Resource<Boolean>>()
    val contactState: LiveData<Resource<Boolean>> = _contactState

    private val _contacts = MutableLiveData<Resource<List<EmergencyContact>>>()
    val contacts: LiveData<Resource<List<EmergencyContact>>> = _contacts

    private val _alertState = MutableLiveData<Resource<Boolean>>()
    val alertState: LiveData<Resource<Boolean>> = _alertState

    fun addContact(userId: String, name: String, email: String, phone: String, relation: String) {
        val contact = EmergencyContact(
            userId = userId,
            name = name,
            email = email,
            phone = phone,
            relation = relation
        )
        addEmergencyContactUseCase(contact).onEach { result ->
            _contactState.value = result
        }.launchIn(viewModelScope)
    }

    fun getContacts(userId: String) {
        getEmergencyContactsUseCase(userId).onEach { result ->
            _contacts.value = result
        }.launchIn(viewModelScope)
    }

    fun sendAlert(userId: String, contactId: String, message: String) {
        val alert = EmergencyAlert(
            userId = userId,
            contactId = contactId,
            message = message
        )
        sendEmergencyAlertUseCase(alert).onEach { result ->
            _alertState.value = result
        }.launchIn(viewModelScope)
    }
}
