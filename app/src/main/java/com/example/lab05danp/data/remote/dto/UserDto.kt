package com.example.lab05danp.data.remote.dto

data class UserDto(
    val id: String,
    val email: String,
    val user_metadata: UserMetadataDto?
)

data class UserMetadataDto(
    val username: String?,
    val firstname: String?,
    val lastname: String?,
    val phone: String?
)

data class RegisterRequestDto(
    val email: String,
    val password: String,
    val data: UserMetadataDto
)
