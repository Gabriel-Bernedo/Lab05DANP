package com.example.lab05danp.ui

import androidx.lifecycle.ViewModel
import com.example.lab05danp.Screen
import com.example.lab05danp.data.model.Order
import com.example.lab05danp.data.model.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class AppViewModel : ViewModel() {

    private val _currentScreen = MutableStateFlow(Screen.LOGIN)
    val currentScreen: StateFlow<Screen> = _currentScreen.asStateFlow()

    private val _selectedProduct = MutableStateFlow<Product?>(null)
    val selectedProduct: StateFlow<Product?> = _selectedProduct.asStateFlow()

    private val _selectedOrder = MutableStateFlow<Order?>(null)
    val selectedOrder: StateFlow<Order?> = _selectedOrder.asStateFlow()

    fun navigateTo(screen: Screen) {
        _currentScreen.value = screen
    }

    fun selectProductAndNavigate(product: Product) {
        _selectedProduct.value = product
        navigateTo(Screen.PRODUCT_DETAIL)
    }

    fun selectOrderAndNavigate(order: Order) {
        _selectedOrder.value = order
        navigateTo(Screen.ORDER_DETAIL)
    }
}
