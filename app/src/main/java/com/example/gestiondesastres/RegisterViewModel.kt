package com.example.gestiondesastres

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gestiondesastres.core.AuthRepository
import com.example.gestiondesastres.core.ResponseService
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RegisterViewModel : ViewModel() {
    private val repository = AuthRepository()

    private val _registerState = MutableStateFlow<ResponseService<FirebaseUser>?>(null)
    val registerState: StateFlow<ResponseService<FirebaseUser>?> = _registerState

    fun signUp(email: String, pass: String) {
        viewModelScope.launch {
            _registerState.value = ResponseService.Loading()
            val result = repository.requestSignUp(email, pass)
            _registerState.value = result
        }
    }
}