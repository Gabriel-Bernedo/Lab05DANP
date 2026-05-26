package com.example.lab05danp.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.lab05danp.data.model.Product
import com.example.lab05danp.data.repository.MockData
import com.example.lab05danp.ui.components.CategoryChips
import com.example.lab05danp.ui.components.ProductCard
import com.example.lab05danp.ui.components.ScreenHeader
import com.example.lab05danp.ui.components.SearchBarRow
import com.example.lab05danp.ui.navigation.AppBottomBar
import com.example.lab05danp.ui.navigation.AppTopBar

@Composable
fun HomeScreen(
    onNavigateToDetails: (Product) -> Unit,
    onNavigateToHistory: () -> Unit,
    onNavigateToCart: () -> Unit
) {
    var searchQuery by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf(MockData.categories.first()) }
    
    val filteredProducts = remember(searchQuery, selectedCategory) {
        MockData.products.filter {
            it.category == selectedCategory && 
            it.name.contains(searchQuery, ignoreCase = true)
        }
    }

    Scaffold(
        topBar = { AppTopBar() },
        bottomBar = { AppBottomBar(
            currentRoute = "inicio", 
            onNavigateToHistory = onNavigateToHistory,
            onNavigateToHome = {},
            onNavigateToCart = onNavigateToCart
        ) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            ScreenHeader(title = "BIENVENIDO !!!\nLO QUE QUIERAS EN MICRO COMPONENTES")
            
            CategoryChips(
                categories = MockData.categories,
                selectedCategory = selectedCategory,
                onCategorySelected = { selectedCategory = it }
            )
            
            SearchBarRow(
                query = searchQuery,
                onQueryChange = { searchQuery = it },
                onSearch = { /* Ya se filtra reactivamente con remember */ }
            )
            
            LazyColumn(modifier = Modifier.weight(1f)) {
                items(filteredProducts) { product ->
                    ProductCard(
                        product = product,
                        onDetailsClick = { onNavigateToDetails(product) }
                    )
                }
            }
        }
    }
}
