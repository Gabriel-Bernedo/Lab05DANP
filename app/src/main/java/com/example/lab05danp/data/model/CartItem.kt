package com.example.lab05danp.data.model

data class CartItem(
    val product: Product,
    var quantity: Int
) {
    val subtotal: Double get() = product.currentPrice * quantity
}
