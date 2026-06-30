package com.example.lab05danp.data.repository

import com.example.lab05danp.data.model.Product

class ProductRepositoryImpl : IProductRepository {
    override suspend fun getProducts(): List<Product> {
        return try {
            com.example.lab05danp.data.remote.RetrofitClient.api.getProducts()
        } catch (e: Exception) {
            emptyList()
        }
    }
}
