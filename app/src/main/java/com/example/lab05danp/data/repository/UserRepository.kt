package com.example.lab05danp.data.repository

import com.example.lab05danp.data.model.User
import com.example.lab05danp.data.remote.RetrofitClient
import com.example.lab05danp.data.remote.dto.LoginRequest
import com.example.lab05danp.data.remote.dto.RegisterRequestDto
import com.example.lab05danp.data.remote.dto.UserMetadataDto

class UserRepositoryImpl : IUserRepository {

    private val users = mutableListOf<User>()

    override suspend fun login(email: String, password: String): User? {
        return try {
            val response = RetrofitClient.api.login(
                LoginRequest(email = email, password = password)
            )
            RetrofitClient.authToken = response.token
            val authUser = response.user
            if (authUser != null) {
                User(authUser.id.hashCode(), "Usuario", email, "hidden_password", "Supabase Address")
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun register(name: String, email: String, password: String): Result<User> {
        return try {
            val names = name.split(" ")
            val firstName = names.firstOrNull() ?: "User"
            val lastName = if (names.size > 1) names.drop(1).joinToString(" ") else ""

            val request = RegisterRequestDto(
                email = email,
                password = password,
                data = UserMetadataDto(
                    username = email,
                    firstname = firstName,
                    lastname = lastName,
                    phone = "000000000"
                )
            )
            
            val response = RetrofitClient.api.registerUser(request)
            
            val authUser = response.user
            if (authUser != null) {
                val user = User(authUser.id.hashCode(), name, email, password, "Supabase Address")
                users.add(user)
                Result.success(user)
            } else {
                Result.failure(Exception("Error al registrar: respuesta vacía"))
            }
        } catch (e: Exception) {
            Result.failure(Exception("Error al registrar en Supabase: ${e.message}"))
        }
    }
}
