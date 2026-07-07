package com.example.lab05danp.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.example.lab05danp.ui.components.OrderCard
import com.example.lab05danp.ui.components.ScreenHeader
import com.example.lab05danp.ui.navigation.AppBottomBar
import com.example.lab05danp.ui.navigation.AppTopBar

@Composable
fun HistoryScreen(
    viewModel: HistoryViewModel,
    onNavigateToHome: () -> Unit,
    onNavigateToCart: () -> Unit,
    onNavigateToOrderDetails: (com.example.lab05danp.data.model.Order) -> Unit
) {
    val orders by viewModel.orders.collectAsState()

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
            
            if (orders.isEmpty()) {
                Box(modifier = Modifier.weight(1f).fillMaxWidth(), contentAlignment = Alignment.Center) {
                    Text("Aún no has realizado ninguna compra", color = MaterialTheme.colorScheme.outline, fontSize = 16.sp)
                }
            } else {
                LazyColumn(modifier = Modifier.weight(1f)) {
                    items(
                        items = orders,
                        key = { order -> order.id }
                    ) { order ->
                        OrderCard(
                            order = order,
                            onDetailsClick = { onNavigateToOrderDetails(order) }
                        )
                    }
                }
            }
        }
    }
}
