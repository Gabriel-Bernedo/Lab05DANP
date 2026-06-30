package com.example.lab05danp.data.local.entity

import androidx.room.Embedded
import androidx.room.Relation

data class OrderWithItems(
    @Embedded val order: OrderEntity,
    @Relation(
        entity = OrderItemEntity::class,
        parentColumn = "id",
        entityColumn = "orderId"
    )
    val items: List<OrderItemWithProduct>
)

data class OrderItemWithProduct(
    @Embedded val orderItem: OrderItemEntity,
    @Relation(
        parentColumn = "productId",
        entityColumn = "id"
    )
    val product: ProductEntity
)
