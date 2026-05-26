package com.example.lab05danp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.lab05danp.data.model.CartItem
import com.example.lab05danp.data.model.Product
import com.example.lab05danp.ui.screens.CartScreen
import com.example.lab05danp.ui.screens.HistoryScreen
import com.example.lab05danp.ui.screens.HomeScreen
import com.example.lab05danp.ui.screens.ProductDetailScreen
import com.example.lab05danp.ui.theme.Lab05DANPTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lab05DANPTheme {
                MarketplaceApp()
            }
        }
    }
}

enum class Screen {
    HOME, HISTORY, CART, PRODUCT_DETAIL
}

@Composable
fun MarketplaceApp() {
    var currentScreen by remember { mutableStateOf(Screen.HOME) }
    var selectedProduct by remember { mutableStateOf<Product?>(null) }
    
    // Global Cart State hoisted here
    var cartItems by remember { mutableStateOf(listOf<CartItem>()) }

    when (currentScreen) {
        Screen.HOME -> {
            HomeScreen(
                onNavigateToDetails = { product ->
                    selectedProduct = product
                    currentScreen = Screen.PRODUCT_DETAIL
                },
                onNavigateToHistory = { currentScreen = Screen.HISTORY },
                onNavigateToCart = { currentScreen = Screen.CART }
            )
        }
        Screen.HISTORY -> {
            HistoryScreen(
                onNavigateToHome = { currentScreen = Screen.HOME },
                onNavigateToCart = { currentScreen = Screen.CART }
            )
        }
        Screen.PRODUCT_DETAIL -> {
            selectedProduct?.let { product ->
                ProductDetailScreen(
                    product = product,
                    onAddToCart = { prod, quantity ->
                        val existingItem = cartItems.find { it.product.id == prod.id }
                        if (existingItem != null) {
                            cartItems = cartItems.map { 
                                if (it.product.id == prod.id) it.copy(quantity = it.quantity + quantity) else it 
                            }
                        } else {
                            cartItems = cartItems + CartItem(prod, quantity)
                        }
                        currentScreen = Screen.CART
                    },
                    onNavigateToHistory = { currentScreen = Screen.HISTORY },
                    onNavigateToHome = { currentScreen = Screen.HOME },
                    onNavigateToCart = { currentScreen = Screen.CART }
                )
            } ?: run {
                currentScreen = Screen.HOME
            }
        }
        Screen.CART -> {
            CartScreen(
                cartItems = cartItems,
                onCheckout = {
                    cartItems = emptyList()
                    currentScreen = Screen.HISTORY
                },
                onNavigateToHistory = { currentScreen = Screen.HISTORY },
                onNavigateToHome = { currentScreen = Screen.HOME }
            )
        }
    }
}