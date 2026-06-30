package com.example.lab05danp.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lab05danp.data.model.Product
import com.example.lab05danp.data.repository.IProductRepository
import com.example.lab05danp.data.repository.ICartRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val productRepository: IProductRepository,
    private val cartRepository: ICartRepository
) : ViewModel() {

    val products: StateFlow<List<Product>> = productRepository.products.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    init {
        viewModelScope.launch {
            productRepository.syncProducts()
            cartRepository.fetchCart() // Sincroniza el carrito globalmente al iniciar el Home
        }
    }
}
