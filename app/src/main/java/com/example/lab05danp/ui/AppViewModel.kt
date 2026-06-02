package com.example.lab05danp.ui

import androidx.lifecycle.ViewModel
import com.example.lab05danp.Screen
import com.example.lab05danp.data.model.CartItem
import com.example.lab05danp.data.model.Product
import com.example.lab05danp.data.model.User
import com.example.lab05danp.data.repository.IProductRepository
import com.example.lab05danp.data.repository.IUserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class AppViewModel(
    val userRepository: IUserRepository,
    val productRepository: IProductRepository
) : ViewModel() {

    private val _currentScreen = MutableStateFlow(Screen.LOGIN)
    val currentScreen: StateFlow<Screen> = _currentScreen.asStateFlow()

    private val _currentUser = MutableStateFlow<User?>(null)
    val currentUser: StateFlow<User?> = _currentUser.asStateFlow()

    private val _selectedProduct = MutableStateFlow<Product?>(null)
    val selectedProduct: StateFlow<Product?> = _selectedProduct.asStateFlow()

    private val _cartItems = MutableStateFlow<List<CartItem>>(emptyList())
    val cartItems: StateFlow<List<CartItem>> = _cartItems.asStateFlow()

    fun navigateTo(screen: Screen) {
        _currentScreen.value = screen
    }

    fun loginSuccess(user: User) {
        _currentUser.value = user
        navigateTo(Screen.HOME)
    }

    fun registerSuccess(user: User) {
        _currentUser.value = user
        navigateTo(Screen.HOME)
    }

    fun logout() {
        _currentUser.value = null
        _cartItems.value = emptyList()
        navigateTo(Screen.LOGIN)
    }

    fun selectProduct(product: Product) {
        _selectedProduct.value = product
        navigateTo(Screen.PRODUCT_DETAIL)
    }

    fun addToCart(product: Product, quantity: Int) {
        _cartItems.update { currentCart ->
            val existing = currentCart.find { it.product.id == product.id }
            if (existing != null) {
                currentCart.map {
                    if (it.product.id == product.id) it.copy(quantity = it.quantity + quantity) else it
                }
            } else {
                currentCart + CartItem(product, quantity)
            }
        }
        navigateTo(Screen.CART)
    }

    fun clearCart() {
        _cartItems.value = emptyList()
    }
}
