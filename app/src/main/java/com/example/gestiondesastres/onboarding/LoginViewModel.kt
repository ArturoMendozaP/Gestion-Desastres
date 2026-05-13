package com.example.gestiondesastres.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gestiondesastres.core.AuthRepository
import com.example.gestiondesastres.core.ResponseService
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    private val repository = AuthRepository()

    private val _loginState = MutableStateFlow<ResponseService<FirebaseUser>?>(null)
    val loginState: StateFlow<ResponseService<FirebaseUser>?> = _loginState

    fun signIn(email: String, pass: String) {
        viewModelScope.launch {
            _loginState.value = ResponseService.Loading()
            val result = repository.requestLogin(email, pass)
            _loginState.value = result
        }
    }
}