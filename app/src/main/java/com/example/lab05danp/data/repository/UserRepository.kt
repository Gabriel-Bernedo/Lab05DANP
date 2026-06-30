package com.example.lab05danp.data.repository

import com.example.lab05danp.data.model.User

class UserRepositoryImpl : IUserRepository {

    // Base de datos en memoria
    private val users = mutableListOf(
        User(1, "Admin Demo", "demo@mail.com", "1234", "Av. Arequipa 123")
    )
    private var nextId = 2

    override suspend fun login(email: String, password: String): User? {
        return try {
            val response = com.example.lab05danp.data.remote.RetrofitClient.api.login(
                com.example.lab05danp.data.remote.dto.LoginRequest(username = email, password = password)
            )
            // FakeStore solo retorna el token, mockeamos el User para la app
            User(nextId++, email, email, "hidden_password", "FakeStore Address")
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun register(name: String, email: String, password: String): Result<User> {
        return try {
            val names = name.split(" ")
            val firstName = names.firstOrNull() ?: "User"
            val lastName = if (names.size > 1) names.drop(1).joinToString(" ") else ""

            val request = com.example.lab05danp.data.remote.dto.RegisterRequestDto(
                email = email,
                username = email, // Usamos el email como username
                password = password,
                name = com.example.lab05danp.data.remote.dto.NameDto(firstName, lastName),
                address = com.example.lab05danp.data.remote.dto.AddressDto("City", "Street", 1, "0000", com.example.lab05danp.data.remote.dto.GeolocationDto("0", "0")),
                phone = "000000000"
            )
            
            val response = com.example.lab05danp.data.remote.RetrofitClient.api.registerUser(request)
            
            // Mockeamos la sesión local para que el flujo no se rompa
            val user = User(response.id, name, email, password, "FakeStore Address")
            users.add(user)
            Result.success(user)
        } catch (e: Exception) {
            Result.failure(Exception("Error al registrar en FakeStore: ${e.message}"))
        }
    }
}
