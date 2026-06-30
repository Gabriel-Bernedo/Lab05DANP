package com.example.lab05danp.data.repository

import com.example.lab05danp.data.model.Product

interface IProductRepository {
    suspend fun getProducts(): List<Product>
}
