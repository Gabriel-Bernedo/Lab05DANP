package com.example.lab05danp.ui.screens

import androidx.lifecycle.ViewModel
import com.example.lab05danp.data.repository.ISessionRepository
import com.example.lab05danp.data.repository.IUserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class RegisterViewModel(
    private val userRepository: IUserRepository,
    private val sessionRepository: ISessionRepository
) : ViewModel() {

    private val _name = MutableStateFlow("")
    val name = _name.asStateFlow()

    private val _email = MutableStateFlow("")
    val email = _email.asStateFlow()

    private val _password = MutableStateFlow("")
    val password = _password.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage = _errorMessage.asStateFlow()

    fun updateName(name: String) {
        _name.value = name
        _errorMessage.value = null
    }

    fun updateEmail(email: String) {
        _email.value = email
        _errorMessage.value = null
    }

    fun updatePassword(password: String) {
        _password.value = password
        _errorMessage.value = null
    }

    fun register(): Boolean {
        if (_name.value.isBlank() || _email.value.isBlank() || _password.value.isBlank()) {
            _errorMessage.value = "Por favor completa todos los campos"
            return false
        }

        val result = userRepository.register(_name.value, _email.value, _password.value)
        return if (result.isSuccess) {
            val user = result.getOrNull()
            if (user != null) {
                sessionRepository.loginUser(user)
                true
            } else {
                _errorMessage.value = "Error al iniciar sesión post-registro"
                false
            }
        } else {
            _errorMessage.value = result.exceptionOrNull()?.message ?: "Error al registrar"
            false
        }
    }
}
