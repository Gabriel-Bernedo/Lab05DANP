package com.example.lab05danp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lab05danp.Screen
import com.example.lab05danp.data.model.Order
import com.example.lab05danp.data.model.Product
import com.example.lab05danp.data.repository.ISessionRepository
import com.example.lab05danp.util.NetworkMonitor
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(
    private val sessionRepository: ISessionRepository,
    private val networkMonitor: NetworkMonitor
) : ViewModel() {

    private val _currentScreen = MutableStateFlow(Screen.LOGIN)
    val currentScreen: StateFlow<Screen> = _currentScreen.asStateFlow()

    private val _selectedProduct = MutableStateFlow<Product?>(null)
    val selectedProduct: StateFlow<Product?> = _selectedProduct.asStateFlow()

    private val _selectedOrder = MutableStateFlow<Order?>(null)
    val selectedOrder: StateFlow<Order?> = _selectedOrder.asStateFlow()

    init {
        viewModelScope.launch {
            delay(500) // Esperar a que Room cargue el usuario
            val hasUser = sessionRepository.currentUser.value != null
            val hasInternet = networkMonitor.isConnected()
            
            if (!hasInternet && !hasUser) {
                navigateTo(Screen.NO_CONNECTION)
            } else if (hasUser) {
                navigateTo(Screen.HOME)
            }
        }
    }

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
