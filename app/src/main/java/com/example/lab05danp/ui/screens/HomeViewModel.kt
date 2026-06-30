package com.example.lab05danp.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lab05danp.data.model.Product
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
    private val productRepository: IProductRepository
) : ViewModel() {

    private val _searchQuery = MutableStateFlow("")
    private val _selectedCategory = MutableStateFlow(MockData.categories.first())
    private val _allProducts = MutableStateFlow<List<Product>>(emptyList())

    init {
        fetchProducts()
    }

    private fun fetchProducts() {
        viewModelScope.launch {
            val products = productRepository.getProducts()
            _allProducts.value = products
        }
    }

    // UiState centralizado que combina todos los flujos de estado de la vista
    val uiState: StateFlow<HomeUiState> = combine(
        _allProducts,
        _searchQuery,
        _selectedCategory
    ) { products, query, category ->
        val filtered = products.filter {
            // it.category == category &&
            it.name.contains(query, ignoreCase = true)
        }
        
        HomeUiState(
            searchQuery = query,
            selectedCategory = category,
            categories = MockData.categories,
            filteredProducts = filtered,
            isLoading = false
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = HomeUiState(categories = MockData.categories, selectedCategory = MockData.categories.first())
    )

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun selectCategory(category: String) {
        _selectedCategory.value = category
    }
}
