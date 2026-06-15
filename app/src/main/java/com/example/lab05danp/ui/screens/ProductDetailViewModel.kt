package com.example.lab05danp.ui.screens

import androidx.lifecycle.ViewModel
import com.example.lab05danp.data.model.Product
import com.example.lab05danp.data.repository.ICartRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

import com.example.lab05danp.domain.session.ISessionManager

import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    private val cartRepository: ICartRepository,
    private val sessionManager: ISessionManager
) : ViewModel() {

    private val _quantity = MutableStateFlow(1)
    val quantity = _quantity.asStateFlow()

    fun increaseQuantity() {
        _quantity.value++
    }

    fun decreaseQuantity() {
        if (_quantity.value > 1) {
            _quantity.value--
        }
    }

    fun addToCart(product: Product) {
        if (_quantity.value > 0) {
            cartRepository.addToCart(product, _quantity.value)
            _quantity.value = 1 // Reset after adding
        }
    }

    fun recordVisit(product: Product) {
        sessionManager.recordProductVisit(product)
    }
}
