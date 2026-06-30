package com.example.lab05danp.data.repository

import com.example.lab05danp.data.model.CartItem
import com.example.lab05danp.data.model.Product
import com.example.lab05danp.data.remote.RetrofitClient
import com.example.lab05danp.data.remote.dto.CartItemRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

interface ICartRepository {
    val cartItems: StateFlow<List<CartItem>>
    suspend fun fetchCart()
    suspend fun addToCart(product: Product, quantity: Int)
    suspend fun clearCart()
}

class CartRepositoryImpl : ICartRepository {
    private val _cartItems = MutableStateFlow<List<CartItem>>(emptyList())
    override val cartItems: StateFlow<List<CartItem>> = _cartItems.asStateFlow()

    override suspend fun fetchCart() {
        try {
            val response = RetrofitClient.api.getCart()
            _cartItems.value = response.items.map { it.toDomainModel() }
        } catch (e: Exception) {
            e.printStackTrace()
            // Podríamos manejar errores de red o token expirado aquí
        }
    }

    override suspend fun addToCart(product: Product, quantity: Int) {
        try {
            val response = RetrofitClient.api.addToCart(CartItemRequest(product.id, quantity))
            _cartItems.value = response.items.map { it.toDomainModel() }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override suspend fun clearCart() {
        try {
            RetrofitClient.api.clearCart()
            _cartItems.value = emptyList()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
