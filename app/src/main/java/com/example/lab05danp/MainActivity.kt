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
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.lab05danp.data.repository.CartRepositoryImpl
import com.example.lab05danp.data.repository.OrderRepositoryImpl
import com.example.lab05danp.data.repository.ProductRepositoryImpl
import com.example.lab05danp.data.repository.SessionRepositoryImpl
import com.example.lab05danp.data.repository.UserRepositoryImpl
import com.example.lab05danp.ui.AppViewModel

import com.example.lab05danp.ui.screens.CartScreen
import com.example.lab05danp.ui.screens.CartViewModel
import com.example.lab05danp.ui.screens.CheckoutScreen
import com.example.lab05danp.ui.screens.CheckoutViewModel
import com.example.lab05danp.ui.screens.HistoryScreen
import com.example.lab05danp.ui.screens.HistoryViewModel
import com.example.lab05danp.ui.screens.HomeScreen
import com.example.lab05danp.ui.screens.HomeViewModel
import com.example.lab05danp.ui.screens.LoginScreen
import com.example.lab05danp.ui.screens.LoginViewModel
import com.example.lab05danp.ui.screens.OrderDetailScreen
import com.example.lab05danp.ui.screens.ProductDetailScreen
import com.example.lab05danp.ui.screens.ProductDetailViewModel
import com.example.lab05danp.ui.screens.ProfileScreen
import com.example.lab05danp.ui.screens.ProfileViewModel
import com.example.lab05danp.ui.screens.RegisterScreen
import com.example.lab05danp.ui.screens.RegisterViewModel
import com.example.lab05danp.ui.theme.Lab05DANPTheme

import dagger.hilt.android.AndroidEntryPoint
import androidx.hilt.navigation.compose.hiltViewModel

@AndroidEntryPoint
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
                    // Navigation ViewModel
                    val appViewModel: AppViewModel = hiltViewModel()

                    MarketplaceApp(appViewModel)
                }
            }
        }
    }
}

enum class Screen {
    LOGIN, REGISTER, HOME, HISTORY, CART, CHECKOUT, PRODUCT_DETAIL, ORDER_DETAIL, PROFILE, NO_CONNECTION
}

@Composable
fun MarketplaceApp(appViewModel: AppViewModel) {

    val currentScreen by appViewModel.currentScreen.collectAsState()
    val selectedProduct by appViewModel.selectedProduct.collectAsState()
    val selectedOrder by appViewModel.selectedOrder.collectAsState()

    when (currentScreen) {
        Screen.NO_CONNECTION -> {
            androidx.compose.foundation.layout.Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = androidx.compose.ui.Alignment.Center
            ) {
                androidx.compose.material3.Text(
                    text = "Perdón, pero se necesita conectividad para usar la aplicación.",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.error,
                    textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
        Screen.LOGIN -> {
            val viewModel: LoginViewModel = hiltViewModel()
            LoginScreen(
                viewModel = viewModel,
                onLoginSuccess = { appViewModel.navigateTo(Screen.HOME) },
                onNavigateToRegister = { appViewModel.navigateTo(Screen.REGISTER) }
            )
        }

        Screen.REGISTER -> {
            val viewModel: RegisterViewModel = hiltViewModel()
            RegisterScreen(
                viewModel = viewModel,
                onRegisterSuccess = { appViewModel.navigateTo(Screen.HOME) },
                onNavigateToLogin = { appViewModel.navigateTo(Screen.LOGIN) }
            )
        }

        Screen.HOME -> {
            val viewModel: HomeViewModel = hiltViewModel()
            HomeScreen(
                viewModel = viewModel,
                onNavigateToDetails = { product -> appViewModel.selectProductAndNavigate(product) },
                onNavigateToHistory = { appViewModel.navigateTo(Screen.HISTORY) },
                onNavigateToCart = { appViewModel.navigateTo(Screen.CART) },
                onNavigateToProfile = { appViewModel.navigateTo(Screen.PROFILE) }
            )
        }

        Screen.HISTORY -> {
            val viewModel: HistoryViewModel = hiltViewModel()
            HistoryScreen(
                viewModel = viewModel,
                onNavigateToHome = { appViewModel.navigateTo(Screen.HOME) },
                onNavigateToCart = { appViewModel.navigateTo(Screen.CART) },
                onNavigateToOrderDetails = { order -> appViewModel.selectOrderAndNavigate(order) }
            )
        }

        Screen.PRODUCT_DETAIL -> {
            selectedProduct?.let { product ->
                val viewModel: ProductDetailViewModel = hiltViewModel()
                ProductDetailScreen(
                    viewModel = viewModel,
                    product = product,
                    onNavigateToHistory = { appViewModel.navigateTo(Screen.HISTORY) },
                    onNavigateToHome = { appViewModel.navigateTo(Screen.HOME) },
                    onNavigateToCart = { appViewModel.navigateTo(Screen.CART) }
                )
            } ?: run { appViewModel.navigateTo(Screen.HOME) }
        }

        Screen.ORDER_DETAIL -> {
            selectedOrder?.let { order ->
                OrderDetailScreen(
                    order = order,
                    onNavigateToHistory = { appViewModel.navigateTo(Screen.HISTORY) },
                    onNavigateToHome = { appViewModel.navigateTo(Screen.HOME) },
                    onNavigateToCart = { appViewModel.navigateTo(Screen.CART) },
                    onNavigateToProfile = { appViewModel.navigateTo(Screen.PROFILE) }
                )
            } ?: run { appViewModel.navigateTo(Screen.HISTORY) }
        }

        Screen.CART -> {
            val viewModel: CartViewModel = hiltViewModel()
            CartScreen(
                viewModel = viewModel,
                onCheckout = { appViewModel.navigateTo(Screen.CHECKOUT) },
                onNavigateToHistory = { appViewModel.navigateTo(Screen.HISTORY) },
                onNavigateToHome = { appViewModel.navigateTo(Screen.HOME) },
                onNavigateToProfile = { appViewModel.navigateTo(Screen.PROFILE) }
            )
        }

        Screen.CHECKOUT -> {
            val viewModel: CheckoutViewModel = hiltViewModel()
            CheckoutScreen(
                viewModel = viewModel,
                onConfirmSuccess = { appViewModel.navigateTo(Screen.HISTORY) },
                onNavigateToHistory = { appViewModel.navigateTo(Screen.HISTORY) },
                onNavigateToHome = { appViewModel.navigateTo(Screen.HOME) },
                onNavigateToCart = { appViewModel.navigateTo(Screen.CART) },
                onNavigateToProfile = { appViewModel.navigateTo(Screen.PROFILE) }
            )
        }

        Screen.PROFILE -> {
            val viewModel: ProfileViewModel = hiltViewModel()
            ProfileScreen(
                viewModel = viewModel,
                onLogoutSuccess = { appViewModel.navigateTo(Screen.LOGIN) },
                onNavigateToHistory = { appViewModel.navigateTo(Screen.HISTORY) },
                onNavigateToHome = { appViewModel.navigateTo(Screen.HOME) },
                onNavigateToCart = { appViewModel.navigateTo(Screen.CART) }
            )
        }
    }
}