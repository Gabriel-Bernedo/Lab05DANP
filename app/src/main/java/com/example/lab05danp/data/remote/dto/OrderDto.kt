package com.example.lab05danp.data.remote.dto

data class OrderDto(
    val id: Int,
    val date: String,
    val status: String,
    val totalAmount: Double,
    val items: List<OrderItemDto>
)

data class OrderItemDto(
    val productId: Int,
    val quantity: Int
)
