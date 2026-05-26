package com.example.lab05danp.data.model

data class Product(
    val id: Int,
    val name: String,
    val originalPrice: Double,
    val discountPrice: Double? = null,
    val description: String = "Lorem ipsum dolor sit amet...",
    val category: String = "Electronicos"
) {
    val currentPrice: Double get() = discountPrice ?: originalPrice
}
