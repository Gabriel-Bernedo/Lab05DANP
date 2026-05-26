package com.example.lab05danp.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.lab05danp.data.repository.MockData
import com.example.lab05danp.ui.components.OrderCard
import com.example.lab05danp.ui.components.ScreenHeader
import com.example.lab05danp.ui.navigation.AppBottomBar
import com.example.lab05danp.ui.navigation.AppTopBar

@Composable
fun HistoryScreen(
    onNavigateToHome: () -> Unit,
    onNavigateToCart: () -> Unit
) {
    Scaffold(
        topBar = { AppTopBar() },
        bottomBar = { AppBottomBar(
            currentRoute = "historial",
            onNavigateToHistory = {},
            onNavigateToHome = onNavigateToHome,
            onNavigateToCart = onNavigateToCart
        ) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            ScreenHeader(title = "REVISE SUS ANTERIORES COMPRAS\nEN LA PLATAFORMA")
            
            LazyColumn(modifier = Modifier.weight(1f)) {
                items(MockData.orders) { order ->
                    OrderCard(
                        order = order,
                        onDetailsClick = { /* Navigate to order details if needed */ }
                    )
                }
            }
        }
    }
}
