package com.example.lab05danp.data.repository

import com.example.lab05danp.data.model.Product
import kotlinx.coroutines.flow.Flow

interface IProductRepository {
    fun getProducts(): Flow<List<Product>>
    suspend fun syncProducts()
}
