package com.example.lab05danp.ui.screens

import androidx.lifecycle.ViewModel
import com.example.lab05danp.data.model.Order
import com.example.lab05danp.data.repository.ICartRepository
import com.example.lab05danp.data.repository.IOrderRepository
import com.example.lab05danp.data.repository.ISessionRepository
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CheckoutViewModel @Inject constructor(
    private val cartRepository: ICartRepository,
    private val sessionRepository: ISessionRepository,
    private val orderRepository: IOrderRepository
) : ViewModel() {

    val cartItems = cartRepository.cartItems
    val currentUser = sessionRepository.currentUser

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage = _errorMessage.asStateFlow()

    fun confirmOrder(address: String): Boolean {
        if (address.isBlank()) {
            _errorMessage.value = "La dirección de entrega es obligatoria"
            return false
        }

        val items = cartItems.value
        if (items.isEmpty()) {
            _errorMessage.value = "El carrito está vacío"
            return false
        }

        val totalAmount = items.sumOf { it.subtotal }
        val date = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date())
        
        // Generar un ID simple en memoria basado en el tiempo
        val orderId = (System.currentTimeMillis() % 100000).toInt()

        val newOrder = Order(
            id = orderId,
            date = date,
            status = "PENDIENTE",
            totalAmount = totalAmount,
            items = items
        )

        orderRepository.addOrder(newOrder)
        viewModelScope.launch {
            cartRepository.clearCart()
        }
        _errorMessage.value = null
        return true
    }

    fun clearError() {
        _errorMessage.value = null
    }
}
