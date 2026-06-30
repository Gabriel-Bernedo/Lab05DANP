package com.example.lab05danp.data.repository

import com.example.lab05danp.data.model.CartItem
import com.example.lab05danp.data.model.Order
import com.example.lab05danp.data.model.Product
import com.example.lab05danp.data.local.dao.OrderDao
import com.example.lab05danp.data.local.entity.OrderEntity
import com.example.lab05danp.data.local.entity.OrderItemEntity
import com.example.lab05danp.data.remote.RetrofitClient
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

interface IOrderRepository {
    val orders: Flow<List<Order>>
    suspend fun fetchOrders()
    suspend fun addOrder(order: Order)
}

class OrderRepositoryImpl @Inject constructor(
    private val orderDao: OrderDao
) : IOrderRepository {

    override val orders: Flow<List<Order>> = orderDao.getOrders().map { items ->
        items.map { orderWithItems ->
            Order(
                id = orderWithItems.order.id,
                date = orderWithItems.order.date,
                status = orderWithItems.order.status,
                totalAmount = orderWithItems.order.totalAmount,
                items = orderWithItems.items.map { item ->
                    CartItem(
                        product = Product(item.product),
                        quantity = item.orderItem.quantity
                    )
                }
            )
        }
    }

    override suspend fun fetchOrders() {
        try {
            val dtos = RetrofitClient.api.getOrders()
            // Limpiar primero
            orderDao.clearOrders()
            dtos.forEach { dto ->
                val orderEntity = OrderEntity(
                    id = dto.id,
                    date = dto.date,
                    status = dto.status,
                    totalAmount = dto.totalAmount
                )
                orderDao.insertOrder(orderEntity)
                val items = dto.items.map { itemDto ->
                    OrderItemEntity(
                        orderId = dto.id,
                        productId = itemDto.productId,
                        quantity = itemDto.quantity
                    )
                }
                orderDao.insertOrderItems(items)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override suspend fun addOrder(order: Order) {
        // 1. Guardado local optimista
        val localOrder = OrderEntity(
            id = order.id,
            date = order.date,
            status = order.status,
            totalAmount = order.totalAmount
        )
        orderDao.insertOrder(localOrder)
        val localItems = order.items.map { cartItem ->
            OrderItemEntity(
                orderId = order.id,
                productId = cartItem.product.id,
                quantity = cartItem.quantity
            )
        }
        orderDao.insertOrderItems(localItems)

        // 2. Intento remoto
        try {
            val response = RetrofitClient.api.createOrder()
            // Actualizar localmente con el ID real del servidor
            val serverOrder = OrderEntity(
                id = response.id,
                date = response.date,
                status = response.status,
                totalAmount = response.totalAmount
            )
            // Eliminamos la orden falsa
            // NOTA: Para producción usar UUIDs, aquí solo reconstruimos
            orderDao.clearOrders()
            fetchOrders() 
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
