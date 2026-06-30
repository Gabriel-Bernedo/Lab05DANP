package com.example.lab05danp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "orders")
data class OrderEntity(
    @PrimaryKey val id: Int,
    val date: String,
    val status: String,
    val totalAmount: Double
)
