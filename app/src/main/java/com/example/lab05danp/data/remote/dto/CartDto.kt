package com.example.lab05danp.data.remote.dto

import com.example.lab05danp.data.model.Product
import com.example.lab05danp.data.model.CartItem

data class CartDto(
    val id: Int,
    val userId: Int,
    val items: List<CartItemDto>
)

data class CartItemDto(
    val id: Int,
    val cartId: Int,
    val productId: Int,
    val quantity: Int,
    val product: Product
) {
    fun toDomainModel(): CartItem {
        return CartItem(
            product = product,
            quantity = quantity
        )
    }
}
