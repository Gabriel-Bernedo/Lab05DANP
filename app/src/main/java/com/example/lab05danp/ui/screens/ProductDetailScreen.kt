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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.draw.shadow
import com.example.lab05danp.data.model.Product
import com.example.lab05danp.ui.components.QuantitySelector
import com.example.lab05danp.ui.components.ScreenHeader
import com.example.lab05danp.ui.navigation.AppBottomBar
import com.example.lab05danp.ui.navigation.AppTopBar
import kotlinx.coroutines.launch

@Composable
fun ProductDetailScreen(
    viewModel: ProductDetailViewModel,
    product: Product,
    onNavigateToHistory: () -> Unit,
    onNavigateToHome: () -> Unit,
    onNavigateToCart: () -> Unit
) {
    val quantity by viewModel.quantity.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(product.id) {
        viewModel.recordVisit(product)
    }

    Scaffold(
        topBar = { AppTopBar() },
        snackbarHost = { SnackbarHost(snackbarHostState) },
        bottomBar = { AppBottomBar(
            currentRoute = "inicio",
            onNavigateToHistory = onNavigateToHistory,
            onNavigateToHome = onNavigateToHome,
            onNavigateToCart = onNavigateToCart,
            onNavigateToProfile = {}
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
                        .background(MaterialTheme.colorScheme.surface)
                        .border(1.dp, MaterialTheme.colorScheme.outline)
                )
                
                Spacer(modifier = Modifier.height(16.dp))
                
                Text(
                    text = product.name,
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 24.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(16.dp))
                        .padding(8.dp)
                )
                
                Spacer(modifier = Modifier.height(8.dp))
                
                Text(
                    text = "Precio: ${product.currentPrice.toInt()} USD",
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 18.sp
                )
                
                Spacer(modifier = Modifier.height(8.dp))
                
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .shadow(elevation = 6.dp, shape = RoundedCornerShape(16.dp))
                        .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(16.dp))
                        .padding(16.dp)
                ) {
                    Text(
                        text = "Descripción:\n${product.description}",
                        color = MaterialTheme.colorScheme.primary,
                        fontSize = 14.sp
                    )
                }
            }
            
            // Bottom Action Section
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(elevation = 6.dp, shape = RoundedCornerShape(16.dp))
                    .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(16.dp))
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text("TOTAL: ${(product.currentPrice * quantity).toInt()} USD", color = MaterialTheme.colorScheme.primary)
                    Spacer(modifier = Modifier.height(8.dp))
                    QuantitySelector(
                        quantity = quantity,
                        onIncrease = { viewModel.increaseQuantity() },
                        onDecrease = { viewModel.decreaseQuantity() }
                    )
                }
                
                Button(
                    onClick = { 
                        viewModel.addToCart(product)
                        coroutineScope.launch {
                            snackbarHostState.showSnackbar("Producto añadido al carrito")
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.surface),
                    modifier = Modifier.shadow(4.dp, RoundedCornerShape(8.dp))
                ) {
                    Text("Añadir al Carrito", color = MaterialTheme.colorScheme.primary)
                }
            }
        }
    }
}
