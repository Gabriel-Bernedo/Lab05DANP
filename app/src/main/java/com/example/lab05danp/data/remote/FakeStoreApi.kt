package com.example.lab05danp.data.remote

import com.example.lab05danp.data.model.Product
import com.example.lab05danp.data.remote.dto.AuthResponse
import com.example.lab05danp.data.remote.dto.LoginRequest
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface FakeStoreApi {
    @GET("products")
    suspend fun getProducts(): List<Product>

    @POST("auth/login")
    suspend fun login(@Body request: LoginRequest): AuthResponse

    @GET("users")
    suspend fun getUsers(): List<com.example.lab05danp.data.remote.dto.UserDto>

    @POST("users")
    suspend fun registerUser(@Body request: com.example.lab05danp.data.remote.dto.RegisterRequestDto): com.example.lab05danp.data.remote.dto.UserDto
}

object RetrofitClient {
    private const val BASE_URL = "https://fakestoreapi.com/"

    val api: FakeStoreApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(FakeStoreApi::class.java)
    }
}
