package com.example.lab05danp.data.remote.dto

data class UserDto(
    val id: Int,
    val email: String,
    val username: String,
    val name: NameDto,
    val address: AddressDto,
    val phone: String
)

data class NameDto(
    val firstname: String,
    val lastname: String
)

data class AddressDto(
    val city: String,
    val street: String,
    val number: Int,
    val zipcode: String,
    val geolocation: GeolocationDto
)

data class GeolocationDto(
    val lat: String,
    val long: String
)

data class RegisterRequestDto(
    val email: String,
    val username: String,
    val password: String,
    val name: NameDto,
    val address: AddressDto,
    val phone: String
)
