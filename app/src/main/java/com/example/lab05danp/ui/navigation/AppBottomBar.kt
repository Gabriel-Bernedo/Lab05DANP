package com.example.lab05danp.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

import androidx.compose.material.icons.filled.List
import androidx.compose.ui.graphics.Color

@Composable
fun AppBottomBar(
    currentRoute: String = "inicio",
    onNavigateToHistory: () -> Unit = {},
    onNavigateToHome: () -> Unit = {},
    onNavigateToCart: () -> Unit = {}
) {
    NavigationBar(containerColor = Color(0xFFFFF59D)) {
        NavigationBarItem(
            selected = currentRoute == "historial",
            onClick = onNavigateToHistory,
            icon = { Icon(Icons.Default.List, contentDescription = null, tint = Color(0xFF1565C0)) },
            label = { Text("Historial", color = Color(0xFF1565C0)) }
        )
        NavigationBarItem(
            selected = currentRoute == "inicio",
            onClick = onNavigateToHome,
            icon = { Icon(Icons.Default.Home, contentDescription = null, tint = Color(0xFF1565C0)) },
            label = { Text("Inicio", color = Color(0xFF1565C0)) }
        )
        NavigationBarItem(
            selected = currentRoute == "carrito",
            onClick = onNavigateToCart,
            icon = { Icon(Icons.Default.ShoppingCart, contentDescription = null, tint = Color(0xFF1565C0)) },
            label = { Text("Carrito", color = Color(0xFF1565C0)) }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AppBottomBarPreview() {
    AppBottomBar()
}
