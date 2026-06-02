package com.example.lab05danp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.example.lab05danp.data.repository.ProductRepositoryImpl
import com.example.lab05danp.data.repository.UserRepositoryImpl
import com.example.lab05danp.ui.AppViewModel
import com.example.lab05danp.ui.screens.CartScreen
import com.example.lab05danp.ui.screens.CheckoutScreen
import com.example.lab05danp.ui.screens.HistoryScreen
import com.example.lab05danp.ui.screens.HomeScreen
import com.example.lab05danp.ui.screens.LoginScreen
import com.example.lab05danp.ui.screens.ProductDetailScreen
import com.example.lab05danp.ui.screens.ProfileScreen
import com.example.lab05danp.ui.screens.RegisterScreen
import com.example.lab05danp.ui.theme.Lab05DANPTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lab05DANPTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val viewModel = remember {
                        AppViewModel(
                            userRepository = UserRepositoryImpl(),
                            productRepository = ProductRepositoryImpl()
                        )
                    }
                    MarketplaceApp(viewModel)
                }
            }
        }
    }
}

enum class Screen {
    LOGIN, REGISTER, HOME, HISTORY, CART, CHECKOUT, PRODUCT_DETAIL, PROFILE
}

@Composable
fun MarketplaceApp(viewModel: AppViewModel) {

    val currentScreen by viewModel.currentScreen.collectAsState()
    val currentUser by viewModel.currentUser.collectAsState()
    val selectedProduct by viewModel.selectedProduct.collectAsState()
    val cartItems by viewModel.cartItems.collectAsState()

    when (currentScreen) {

        Screen.LOGIN -> LoginScreen(
            userRepository = viewModel.userRepository as UserRepositoryImpl,
            onLoginSuccess = { user -> viewModel.loginSuccess(user) },
            onNavigateToRegister = { viewModel.navigateTo(Screen.REGISTER) }
        )

        Screen.REGISTER -> RegisterScreen(
            userRepository = viewModel.userRepository as UserRepositoryImpl,
            onRegisterSuccess = { user -> viewModel.registerSuccess(user) },
            onNavigateToLogin = { viewModel.navigateTo(Screen.LOGIN) }
        )

        Screen.HOME -> HomeScreen(
            onNavigateToDetails = { product -> viewModel.selectProduct(product) },
            onNavigateToHistory = { viewModel.navigateTo(Screen.HISTORY) },
            onNavigateToCart = { viewModel.navigateTo(Screen.CART) },
            onNavigateToProfile = { viewModel.navigateTo(Screen.PROFILE) }
        )

        Screen.HISTORY -> HistoryScreen(
            onNavigateToHome = { viewModel.navigateTo(Screen.HOME) },
            onNavigateToCart = { viewModel.navigateTo(Screen.CART) }
        )

        Screen.PRODUCT_DETAIL -> selectedProduct?.let { product ->
            ProductDetailScreen(
                product = product,
                onAddToCart = { prod, quantity -> viewModel.addToCart(prod, quantity) },
                onNavigateToHistory = { viewModel.navigateTo(Screen.HISTORY) },
                onNavigateToHome = { viewModel.navigateTo(Screen.HOME) },
                onNavigateToCart = { viewModel.navigateTo(Screen.CART) }
            )
        } ?: run { viewModel.navigateTo(Screen.HOME) }

        Screen.CART -> CartScreen(
            cartItems = cartItems,
            onCheckout = { viewModel.navigateTo(Screen.CHECKOUT) },
            onNavigateToHistory = { viewModel.navigateTo(Screen.HISTORY) },
            onNavigateToHome = { viewModel.navigateTo(Screen.HOME) },
            onNavigateToProfile = { viewModel.navigateTo(Screen.PROFILE) }
        )

        Screen.CHECKOUT -> currentUser?.let { user ->
            CheckoutScreen(
                cartItems = cartItems,
                user = user,
                onConfirmOrder = {
                    viewModel.clearCart()
                    viewModel.navigateTo(Screen.HISTORY)
                },
                onNavigateToHistory = { viewModel.navigateTo(Screen.HISTORY) },
                onNavigateToHome = { viewModel.navigateTo(Screen.HOME) },
                onNavigateToCart = { viewModel.navigateTo(Screen.CART) },
                onNavigateToProfile = { viewModel.navigateTo(Screen.PROFILE) }
            )
        } ?: run { viewModel.navigateTo(Screen.LOGIN) }

        Screen.PROFILE -> currentUser?.let { user ->
            ProfileScreen(
                user = user,
                onLogout = { viewModel.logout() },
                onNavigateToHistory = { viewModel.navigateTo(Screen.HISTORY) },
                onNavigateToHome = { viewModel.navigateTo(Screen.HOME) },
                onNavigateToCart = { viewModel.navigateTo(Screen.CART) }
            )
        } ?: run { viewModel.navigateTo(Screen.LOGIN) }
    }
}