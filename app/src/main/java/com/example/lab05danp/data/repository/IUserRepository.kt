package com.example.lab05danp.data.repository

import com.example.lab05danp.data.model.User

interface IUserRepository {
    fun login(email: String, password: String): User?
    fun register(name: String, email: String, password: String): Result<User>
}
