package com.example.lab05danp.data.repository

import com.example.lab05danp.data.model.User

class UserRepositoryImpl : IUserRepository {

    // Base de datos en memoria
    private val users = mutableListOf(
        User(1, "Admin Demo", "demo@mail.com", "1234", "Av. Arequipa 123")
    )
    private var nextId = 2

    /** Retorna el usuario si las credenciales son válidas, null en caso contrario. */
    override fun login(email: String, password: String): User? =
        users.find { it.email.trim() == email.trim() && it.password == password }

    /** Registra un nuevo usuario. Retorna error si el email ya existe. */
    override fun register(name: String, email: String, password: String): Result<User> {
        if (users.any { it.email.trim() == email.trim() }) {
            return Result.failure(Exception("El email ya está registrado"))
        }
        val user = User(nextId++, name, email, password)
        users.add(user)
        return Result.success(user)
    }
}
