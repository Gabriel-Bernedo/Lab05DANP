package com.example.lab05danp.data.model

import com.example.lab05danp.data.local.entity.ProductEntity

data class Product(
    val id: Int,
    val title: String,
    val price: Double,
    val description: String,
    val image: String,
    val category: String,
    val lastUpdated: Long = 0L
) {
    constructor(entity: ProductEntity) : this(
        id = entity.id,
        title = entity.title,
        price = entity.price,
        description = entity.description,
        image = entity.image,
        category = entity.category,
        lastUpdated = entity.lastUpdated
    )

    val name: String get() = title
    val currentPrice: Double get() = price
    val originalPrice: Double get() = price
}
