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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.lab05danp.data.repository.CartRepositoryImpl
import com.example.lab05danp.data.repository.OrderRepositoryImpl
import com.example.lab05danp.data.repository.ProductRepositoryImpl
import com.example.lab05danp.data.repository.SessionRepositoryImpl
import com.example.lab05danp.data.repository.UserRepositoryImpl
import com.example.lab05danp.ui.AppViewModel
import com.example.lab05danp.ui.AppViewModelFactory
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
                    // Obtener AppContainer desde la Application
                    val appContainer = (application as MarketplaceApplication).container

                    // Navigation ViewModel
                    val appViewModel = remember { AppViewModel() }

                    // Factory para inyectar dependencias a los ViewModels desde el AppContainer
                    val factory = remember {
                        AppViewModelFactory(
                            userRepository = appContainer.userRepository,
                            productRepository = appContainer.productRepository,
                            sessionRepository = appContainer.sessionRepository,
                            cartRepository = appContainer.cartRepository,
                            orderRepository = appContainer.orderRepository,
                            sessionManager = appContainer.sessionManager,
                            appViewModel = appViewModel
                        )
                    }

                    MarketplaceApp(appViewModel, factory)
                }
            }
        }
    }
}

enum class Screen {
    LOGIN, REGISTER, HOME, HISTORY, CART, CHECKOUT, PRODUCT_DETAIL, ORDER_DETAIL, PROFILE
}

@Composable
fun MarketplaceApp(appViewModel: AppViewModel, factory: AppViewModelFactory) {

    val currentScreen by appViewModel.currentScreen.collectAsState()
    val selectedProduct by appViewModel.selectedProduct.collectAsState()
    val selectedOrder by appViewModel.selectedOrder.collectAsState()

    when (currentScreen) {
        Screen.LOGIN -> {
            val viewModel: LoginViewModel = viewModel(factory = factory)
            LoginScreen(
                viewModel = viewModel,
                onLoginSuccess = { appViewModel.navigateTo(Screen.HOME) },
                onNavigateToRegister = { appViewModel.navigateTo(Screen.REGISTER) }
            )
        }

        Screen.REGISTER -> {
            val viewModel: RegisterViewModel = viewModel(factory = factory)
            RegisterScreen(
                viewModel = viewModel,
                onRegisterSuccess = { appViewModel.navigateTo(Screen.HOME) },
                onNavigateToLogin = { appViewModel.navigateTo(Screen.LOGIN) }
            )
        }

        Screen.HOME -> {
            val viewModel: HomeViewModel = viewModel(factory = factory)
            HomeScreen(
                viewModel = viewModel,
                onNavigateToDetails = { product -> appViewModel.selectProductAndNavigate(product) },
                onNavigateToHistory = { appViewModel.navigateTo(Screen.HISTORY) },
                onNavigateToCart = { appViewModel.navigateTo(Screen.CART) },
                onNavigateToProfile = { appViewModel.navigateTo(Screen.PROFILE) }
            )
        }

        Screen.HISTORY -> {
            val viewModel: HistoryViewModel = viewModel(factory = factory)
            HistoryScreen(
                viewModel = viewModel,
                onNavigateToHome = { appViewModel.navigateTo(Screen.HOME) },
                onNavigateToCart = { appViewModel.navigateTo(Screen.CART) },
                onNavigateToOrderDetails = { order -> appViewModel.selectOrderAndNavigate(order) }
            )
        }

        Screen.PRODUCT_DETAIL -> {
            selectedProduct?.let { product ->
                val viewModel: ProductDetailViewModel = viewModel(factory = factory)
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
            val viewModel: CartViewModel = viewModel(factory = factory)
            CartScreen(
                viewModel = viewModel,
                onCheckout = { appViewModel.navigateTo(Screen.CHECKOUT) },
                onNavigateToHistory = { appViewModel.navigateTo(Screen.HISTORY) },
                onNavigateToHome = { appViewModel.navigateTo(Screen.HOME) },
                onNavigateToProfile = { appViewModel.navigateTo(Screen.PROFILE) }
            )
        }

        Screen.CHECKOUT -> {
            val viewModel: CheckoutViewModel = viewModel(factory = factory)
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
            val viewModel: ProfileViewModel = viewModel(factory = factory)
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