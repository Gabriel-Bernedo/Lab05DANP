package com.example.lab05danp.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.example.lab05danp.data.model.Product
import com.example.lab05danp.ui.components.CategoryChips
import com.example.lab05danp.ui.components.ProductCard
import com.example.lab05danp.ui.components.ScreenHeader
import com.example.lab05danp.ui.components.SearchBarRow
import com.example.lab05danp.ui.navigation.AppBottomBar
import com.example.lab05danp.ui.navigation.AppTopBar

@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    onNavigateToDetails: (Product) -> Unit,
    onNavigateToHistory: () -> Unit,
    onNavigateToCart: () -> Unit,
    onNavigateToProfile: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = { AppTopBar() },
        bottomBar = { AppBottomBar(
            currentRoute = "inicio", 
            onNavigateToHistory = onNavigateToHistory,
            onNavigateToHome = {},
            onNavigateToCart = onNavigateToCart,
            onNavigateToProfile = onNavigateToProfile
        ) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            ScreenHeader(title = "BIENVENIDO !!!\nLO QUE QUIERAS EN MICRO COMPONENTES")
            
            CategoryChips(
                categories = uiState.categories,
                selectedCategory = uiState.selectedCategory,
                onCategorySelected = { viewModel.selectCategory(it) }
            )
            
            SearchBarRow(
                query = uiState.searchQuery,
                onQueryChange = { viewModel.updateSearchQuery(it) },
                onSearch = { /* Filtrado reactivo en ViewModel */ }
            )
            
            LazyColumn(modifier = Modifier.weight(1f)) {
                items(uiState.filteredProducts) { product ->
                    ProductCard(
                        product = product,
                        onDetailsClick = { onNavigateToDetails(product) }
                    )
                }
            }
        }
    }
}
