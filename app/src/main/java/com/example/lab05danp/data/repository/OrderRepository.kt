package com.example.lab05danp.data.repository

import com.example.lab05danp.data.model.Order
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

interface IOrderRepository {
    val orders: StateFlow<List<Order>>
    fun addOrder(order: Order)
    fun getOrdersByUser(userId: String): List<Order>
}

class OrderRepositoryImpl : IOrderRepository {
    private val _orders = MutableStateFlow<List<Order>>(emptyList())
    override val orders: StateFlow<List<Order>> = _orders.asStateFlow()

    override fun addOrder(order: Order) {
        _orders.update { currentOrders -> currentOrders + order }
    }

    override fun getOrdersByUser(userId: String): List<Order> {
        // En este mock simplificado no tenemos userId en Order, pero 
        // simularemos que las guardadas pertenecen al usuario actual.
        // Asumiendo que Order se genera con la sesión actual.
        return _orders.value
    }
}
