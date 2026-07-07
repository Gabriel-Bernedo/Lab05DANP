package com.example.lab05danp.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lab05danp.data.model.Product
import com.example.lab05danp.data.repository.ICartRepository
import com.example.lab05danp.data.repository.IProductRepository
import com.example.lab05danp.data.repository.MockData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

data class HomeUiState(
    val searchQuery: String = "",
    val selectedCategory: String = "",
    val categories: List<String> = emptyList(),
    val filteredProducts: List<Product> = emptyList(),
    val isLoading: Boolean = false
)

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val productRepository: IProductRepository,
    private val cartRepository: ICartRepository
) : ViewModel() {

    private val _searchQuery = MutableStateFlow("")
    private val _selectedCategory = MutableStateFlow(MockData.categories.first())

    init {
        viewModelScope.launch {
            productRepository.syncProducts()
            cartRepository.fetchCart() // Sincroniza el carrito globalmente al iniciar el Home
        }
    }

    // UiState centralizado que combina todos los flujos de estado de la vista
    val uiState: StateFlow<HomeUiState> = combine(
        productRepository.getProducts(),
        _searchQuery,
        _selectedCategory
    ) { products, query, category ->
        
        // Simple client-side filtering logic based on category and query
        val filtered = products.filter { product ->
            val matchesCategory = category.isEmpty() || category == "Todos" || product.category == category
            val matchesQuery = query.isEmpty() || product.title.contains(query, ignoreCase = true)
            matchesCategory && matchesQuery
        }

        HomeUiState(
            searchQuery = query,
            selectedCategory = category,
            categories = listOf("Todos") + MockData.categories,
            filteredProducts = filtered, 
            isLoading = false
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = HomeUiState(categories = listOf("Todos") + MockData.categories, selectedCategory = "Todos")
    )

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun selectCategory(category: String) {
        _selectedCategory.value = category
    }
}
