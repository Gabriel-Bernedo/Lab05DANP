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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lab05danp.data.model.Product
import com.example.lab05danp.ui.components.QuantitySelector
import com.example.lab05danp.ui.components.ScreenHeader
import com.example.lab05danp.ui.navigation.AppBottomBar
import com.example.lab05danp.ui.navigation.AppTopBar

@Composable
fun ProductDetailScreen(
    product: Product,
    onAddToCart: (Product, Int) -> Unit,
    onNavigateToHistory: () -> Unit,
    onNavigateToHome: () -> Unit,
    onNavigateToCart: () -> Unit
) {
    var quantity by remember { mutableStateOf(1) }

    Scaffold(
        topBar = { AppTopBar() },
        bottomBar = { AppBottomBar(
            currentRoute = "inicio",
            onNavigateToHistory = onNavigateToHistory,
            onNavigateToHome = onNavigateToHome,
            onNavigateToCart = onNavigateToCart
        ) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            ScreenHeader(title = "VERIFIQUE LA INFORMACIÓN DEL\nPRODUCTO")
            
            Column(modifier = Modifier.padding(16.dp).weight(1f)) {
                // Large Image Placeholder
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .background(Color.White)
                        .border(1.dp, Color.Gray)
                )
                
                Spacer(modifier = Modifier.height(16.dp))
                
                Text(
                    text = product.name,
                    color = Color(0xFF1565C0),
                    fontSize = 24.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFFFFF59D))
                        .padding(8.dp)
                )
                
                Spacer(modifier = Modifier.height(8.dp))
                
                Text(
                    text = "Precio: ${product.currentPrice.toInt()} USD",
                    color = Color(0xFF1565C0),
                    fontSize = 18.sp
                )
                
                Spacer(modifier = Modifier.height(8.dp))
                
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .background(Color(0xFFFFF59D))
                        .border(1.dp, Color.Gray)
                        .padding(16.dp)
                ) {
                    Text(
                        text = "Descripción:\n${product.description}",
                        color = Color(0xFF1565C0),
                        fontSize = 14.sp
                    )
                }
            }
            
            // Bottom Action Section
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFFFF59D))
                    .border(1.dp, Color.Gray)
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text("TOTAL: ${(product.currentPrice * quantity).toInt()} USD", color = Color(0xFF1565C0))
                    Spacer(modifier = Modifier.height(8.dp))
                    QuantitySelector(
                        quantity = quantity,
                        onIncrease = { quantity++ },
                        onDecrease = { quantity-- }
                    )
                }
                
                Button(
                    onClick = { 
                        if (quantity > 0) {
                            onAddToCart(product, quantity)
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                    modifier = Modifier.border(1.dp, Color(0xFF1565C0))
                ) {
                    Text("Añadir al Carrito", color = Color(0xFF1565C0))
                }
            }
        }
    }
}
