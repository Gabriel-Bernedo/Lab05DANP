package com.example.lab05danp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class ProductEntity(
    @PrimaryKey
    val id: Int,
    val title: String,
    val price: Double,
    val description: String,
    val image: String,
    val category: String,
    val lastUpdated: Long = System.currentTimeMillis()
)
