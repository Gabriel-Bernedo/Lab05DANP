package com.example.lab05danp.data.repository

import com.example.lab05danp.data.model.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

interface ISessionRepository {
    val currentUser: StateFlow<User?>
    fun loginUser(user: User)
    fun logoutUser()
}

class SessionRepositoryImpl : ISessionRepository {
    private val _currentUser = MutableStateFlow<User?>(null)
    override val currentUser: StateFlow<User?> = _currentUser.asStateFlow()

    override fun loginUser(user: User) {
        _currentUser.value = user
    }

    override fun logoutUser() {
        _currentUser.value = null
    }
}
