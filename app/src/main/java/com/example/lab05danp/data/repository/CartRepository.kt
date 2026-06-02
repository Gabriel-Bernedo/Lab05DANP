package com.example.lab05danp.data.repository

import com.example.lab05danp.data.model.CartItem
import com.example.lab05danp.data.model.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

interface ICartRepository {
    val cartItems: StateFlow<List<CartItem>>
    fun addToCart(product: Product, quantity: Int)
    fun clearCart()
}

class CartRepositoryImpl : ICartRepository {
    private val _cartItems = MutableStateFlow<List<CartItem>>(emptyList())
    override val cartItems: StateFlow<List<CartItem>> = _cartItems.asStateFlow()

    override fun addToCart(product: Product, quantity: Int) {
        _cartItems.update { currentCart ->
            val existing = currentCart.find { it.product.id == product.id }
            if (existing != null) {
                currentCart.map {
                    if (it.product.id == product.id) it.copy(quantity = it.quantity + quantity) else it
                }
            } else {
                currentCart + CartItem(product, quantity)
            }
        }
    }

    override fun clearCart() {
        _cartItems.value = emptyList()
    }
}
