package com.example.lab05danp.data.remote.dto

data class LoginRequest(
    val username: String,
    val password: String
)

data class AuthResponse(
    val token: String
)
