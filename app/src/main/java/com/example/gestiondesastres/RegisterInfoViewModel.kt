package com.example.gestiondesastres

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gestiondesastres.core.ResponseService
import com.example.gestiondesastres.core.repositories.UserRepository
import com.example.gestiondesastres.models.UserProfile
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RegisterInfoViewModel : ViewModel() {
    private val repository = UserRepository()

    private val _saveState = MutableStateFlow<ResponseService<Boolean>?>(null)
    val saveState: StateFlow<ResponseService<Boolean>?> = _saveState

    fun saveProfile(userProfile: UserProfile) {
        viewModelScope.launch {
            _saveState.value = ResponseService.Loading()
            val result = repository.saveUserInfo(userProfile)
            _saveState.value = result
        }
    }
}