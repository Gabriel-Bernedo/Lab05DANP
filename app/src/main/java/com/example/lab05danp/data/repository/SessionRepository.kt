package com.example.lab05danp.data.repository

import com.example.lab05danp.data.model.User
import com.example.lab05danp.data.local.dao.UserDao
import com.example.lab05danp.data.local.entity.UserEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

interface ISessionRepository {
    val currentUser: StateFlow<User?>
    fun loginUser(user: User)
    fun logoutUser()
}

class SessionRepositoryImpl @Inject constructor(
    private val userDao: UserDao,
    private val scope: CoroutineScope = CoroutineScope(Dispatchers.IO)
) : ISessionRepository {
    private val _currentUser = MutableStateFlow<User?>(null)
    override val currentUser: StateFlow<User?> = _currentUser.asStateFlow()

    init {
        scope.launch {
            userDao.getUser().collect { entity ->
                _currentUser.value = entity?.toDomain()
            }
        }
    }

    override fun loginUser(user: User) {
        scope.launch {
            userDao.saveUser(UserEntity.fromDomain(user))
        }
    }

    override fun logoutUser() {
        scope.launch {
            userDao.deleteUser()
        }
    }
}
