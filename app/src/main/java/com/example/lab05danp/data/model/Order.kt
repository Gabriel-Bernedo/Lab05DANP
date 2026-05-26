package com.example.lab05danp.data.model

data class Order(
    val id: Int,
    val date: String,
    val status: String, // "PENDIENTE", "ENTREGADO"
    val totalAmount: Double
)
