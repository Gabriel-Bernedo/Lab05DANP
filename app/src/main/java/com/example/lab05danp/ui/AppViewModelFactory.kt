package com.example.lab05danp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.lab05danp.data.repository.ICartRepository
import com.example.lab05danp.data.repository.IOrderRepository
import com.example.lab05danp.data.repository.IProductRepository
import com.example.lab05danp.data.repository.ISessionRepository
import com.example.lab05danp.data.repository.IUserRepository
import com.example.lab05danp.ui.screens.CartViewModel
import com.example.lab05danp.ui.screens.CheckoutViewModel
import com.example.lab05danp.ui.screens.HistoryViewModel
import com.example.lab05danp.ui.screens.HomeViewModel
import com.example.lab05danp.ui.screens.LoginViewModel
import com.example.lab05danp.ui.screens.ProductDetailViewModel
import com.example.lab05danp.ui.screens.ProfileViewModel
import com.example.lab05danp.ui.screens.RegisterViewModel

class AppViewModelFactory(
    private val userRepository: IUserRepository,
    private val productRepository: IProductRepository,
    private val sessionRepository: ISessionRepository,
    private val cartRepository: ICartRepository,
    private val orderRepository: IOrderRepository,
    private val appViewModel: AppViewModel // Usado solo para navegación si se mantiene
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                HomeViewModel(productRepository) as T
            }
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> {
                LoginViewModel(userRepository, sessionRepository) as T
            }
            modelClass.isAssignableFrom(RegisterViewModel::class.java) -> {
                RegisterViewModel(userRepository, sessionRepository) as T
            }
            modelClass.isAssignableFrom(ProfileViewModel::class.java) -> {
                ProfileViewModel(sessionRepository, cartRepository) as T
            }
            modelClass.isAssignableFrom(CartViewModel::class.java) -> {
                CartViewModel(cartRepository) as T
            }
            modelClass.isAssignableFrom(ProductDetailViewModel::class.java) -> {
                ProductDetailViewModel(cartRepository) as T
            }
            modelClass.isAssignableFrom(CheckoutViewModel::class.java) -> {
                CheckoutViewModel(cartRepository, sessionRepository, orderRepository) as T
            }
            modelClass.isAssignableFrom(HistoryViewModel::class.java) -> {
                HistoryViewModel(orderRepository, sessionRepository) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
