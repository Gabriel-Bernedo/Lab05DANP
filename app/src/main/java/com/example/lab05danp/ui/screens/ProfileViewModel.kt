package com.example.lab05danp.ui.screens

import androidx.lifecycle.ViewModel
import com.example.lab05danp.data.repository.ICartRepository
import com.example.lab05danp.data.repository.ISessionRepository

class ProfileViewModel(
    private val sessionRepository: ISessionRepository,
    private val cartRepository: ICartRepository
) : ViewModel() {

    val currentUser = sessionRepository.currentUser

    fun logout() {
        sessionRepository.logoutUser()
        cartRepository.clearCart()
    }
}
