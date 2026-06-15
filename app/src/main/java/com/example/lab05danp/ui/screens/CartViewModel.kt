package com.example.lab05danp.ui.screens

import androidx.lifecycle.ViewModel
import com.example.lab05danp.data.model.CartItem
import com.example.lab05danp.data.repository.ICartRepository
import kotlinx.coroutines.flow.StateFlow

import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val cartRepository: ICartRepository
) : ViewModel() {

    val cartItems: StateFlow<List<CartItem>> = cartRepository.cartItems

    fun clearCart() {
        cartRepository.clearCart()
    }
}
