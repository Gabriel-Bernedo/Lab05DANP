package com.example.lab05danp.ui.screens

import androidx.lifecycle.ViewModel
import com.example.lab05danp.data.repository.ISessionRepository
import com.example.lab05danp.data.repository.IUserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

import kotlinx.coroutines.launch
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userRepository: IUserRepository,
    private val sessionRepository: ISessionRepository
) : ViewModel() {

    private val _email = MutableStateFlow("")
    val email = _email.asStateFlow()

    private val _password = MutableStateFlow("")
    val password = _password.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage = _errorMessage.asStateFlow()

    fun updateEmail(email: String) {
        _email.value = email
        _errorMessage.value = null
    }

    fun updatePassword(password: String) {
        _password.value = password
        _errorMessage.value = null
    }

    fun login(onSuccess: () -> Unit) {
        viewModelScope.launch {
            val user = userRepository.login(_email.value, _password.value)
            if (user != null) {
                sessionRepository.loginUser(user)
                onSuccess()
            } else {
                _errorMessage.value = "Username o contraseña incorrectos"
            }
        }
    }
}
