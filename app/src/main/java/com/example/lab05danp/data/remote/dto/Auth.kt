package com.example.lab05danp.data.remote.dto

import com.google.gson.annotations.SerializedName

data class LoginRequest(
    val email: String,
    val password: String
)

data class AuthResponse(
    @SerializedName("access_token")
    val token: String,
    val user: SupabaseUser?
)

data class SupabaseUser(
    val id: String,
    val email: String
)
