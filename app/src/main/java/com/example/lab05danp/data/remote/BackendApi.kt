package com.example.lab05danp.data.remote

import com.example.lab05danp.data.model.Product
import com.example.lab05danp.data.remote.dto.AuthResponse
import com.example.lab05danp.data.remote.dto.LoginRequest
import com.example.lab05danp.data.remote.dto.UserDto
import com.example.lab05danp.data.remote.dto.RegisterRequestDto
import com.example.lab05danp.data.remote.dto.CartDto
import com.example.lab05danp.data.remote.dto.CartItemRequest
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.DELETE

interface BackendApi {
    @GET("rest/v1/products")
    suspend fun getProducts(): List<Product>

    @POST("auth/v1/token")
    suspend fun login(@Body request: LoginRequest): AuthResponse

    @POST("auth/v1/signup")
    suspend fun registerUser(@Body request: RegisterRequestDto): AuthResponse

    @GET("rest/v1/cart")
    suspend fun getCart(): CartDto

    @POST("rest/v1/cart/items")
    suspend fun addToCart(@Body request: CartItemRequest): CartDto

    @DELETE("rest/v1/cart")
    suspend fun clearCart()

    @GET("rest/v1/orders")
    suspend fun getOrders(): List<com.example.lab05danp.data.remote.dto.OrderDto>

    @POST("rest/v1/orders")
    suspend fun createOrder(): com.example.lab05danp.data.remote.dto.OrderDto
}

object RetrofitClient {
    private const val BASE_URL = "https://danp.test-lab10.auroboros.lat/"

    var authToken: String? = null

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val original = chain.request()
            val requestBuilder = original.newBuilder()
                .header("Content-Type", "application/json")
            
            authToken?.let {
                requestBuilder.header("Authorization", "Bearer $it")
            }
            
            chain.proceed(requestBuilder.build())
        }
        .build()

    val api: BackendApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BackendApi::class.java)
    }
}
