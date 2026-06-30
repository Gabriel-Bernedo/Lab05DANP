package com.example.lab05danp.data.model

data class Product(
    val id: Int,
    val title: String,
    val price: Double,
    val description: String,
    val image: String,
    val category: String
) {
    val name: String get() = title
    val currentPrice: Double get() = price
    val originalPrice: Double get() = price
}
