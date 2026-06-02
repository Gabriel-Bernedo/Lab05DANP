package com.example.lab05danp.ui.screens

import androidx.lifecycle.ViewModel
import com.example.lab05danp.data.repository.IOrderRepository
import com.example.lab05danp.data.repository.ISessionRepository

class HistoryViewModel(
    private val orderRepository: IOrderRepository,
    private val sessionRepository: ISessionRepository
) : ViewModel() {

    // Simular obtención de órdenes por usuario (usando un id mockeado temporal)
    val orders = orderRepository.orders
}
