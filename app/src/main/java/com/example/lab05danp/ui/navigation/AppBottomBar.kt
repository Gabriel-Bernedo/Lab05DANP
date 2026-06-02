package com.example.lab05danp.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material3.MaterialTheme

@Composable
fun AppBottomBar(
    currentRoute: String = "inicio",
    onNavigateToHistory: () -> Unit = {},
    onNavigateToHome: () -> Unit = {},
    onNavigateToCart: () -> Unit = {},
    onNavigateToProfile: () -> Unit = {}
) {
    NavigationBar(containerColor = MaterialTheme.colorScheme.surfaceVariant) {
        NavigationBarItem(
            selected = currentRoute == "historial",
            onClick = onNavigateToHistory,
            icon = { Icon(Icons.Default.List, contentDescription = null, tint = MaterialTheme.colorScheme.primary) },
            label = { Text("Historial", color = MaterialTheme.colorScheme.primary) }
        )
        NavigationBarItem(
            selected = currentRoute == "inicio",
            onClick = onNavigateToHome,
            icon = { Icon(Icons.Default.Home, contentDescription = null, tint = MaterialTheme.colorScheme.primary) },
            label = { Text("Inicio", color = MaterialTheme.colorScheme.primary) }
        )
        NavigationBarItem(
            selected = currentRoute == "carrito",
            onClick = onNavigateToCart,
            icon = { Icon(Icons.Default.ShoppingCart, contentDescription = null, tint = MaterialTheme.colorScheme.primary) },
            label = { Text("Carrito", color = MaterialTheme.colorScheme.primary) }
        )
        NavigationBarItem(
            selected = currentRoute == "perfil",
            onClick = onNavigateToProfile,
            icon = { Icon(Icons.Default.Person, contentDescription = null, tint = MaterialTheme.colorScheme.primary) },
            label = { Text("Perfil", color = MaterialTheme.colorScheme.primary) }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AppBottomBarPreview() {
    AppBottomBar()
}
