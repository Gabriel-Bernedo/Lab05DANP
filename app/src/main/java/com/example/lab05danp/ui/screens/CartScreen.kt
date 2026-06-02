package com.example.lab05danp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lab05danp.data.model.CartItem
import com.example.lab05danp.ui.components.CartItemCard
import com.example.lab05danp.ui.components.ScreenHeader
import com.example.lab05danp.ui.navigation.AppBottomBar
import com.example.lab05danp.ui.navigation.AppTopBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.draw.shadow

@Composable
fun CartScreen(
    cartItems: List<CartItem>,
    onCheckout: () -> Unit,
    onNavigateToHistory: () -> Unit,
    onNavigateToHome: () -> Unit,
    onNavigateToProfile: () -> Unit
) {
    val totalAmount = cartItems.sumOf { it.subtotal }

    Scaffold(
        topBar = { AppTopBar() },
        bottomBar = { AppBottomBar(
            currentRoute = "carrito",
            onNavigateToHistory = onNavigateToHistory,
            onNavigateToHome = onNavigateToHome,
            onNavigateToCart = {},
            onNavigateToProfile = onNavigateToProfile
        ) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            ScreenHeader(title = "VERIFIQUE SUS CONTENIDOS\nANTES DE CONTINUAR")
            
            LazyColumn(modifier = Modifier.weight(1f)) {
                items(cartItems) { item ->
                    CartItemCard(cartItem = item)
                }
            }
            
            // Bottom Checkout Section
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(elevation = 6.dp, shape = RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(16.dp))
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "TOTAL :\n${totalAmount.toInt()} USD",
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                
                Button(
                    onClick = onCheckout,
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.surface),
                    modifier = Modifier.shadow(4.dp, RoundedCornerShape(8.dp))
                ) {
                    Text("ORDENAR", color = MaterialTheme.colorScheme.primary)
                }
            }
        }
    }
}
