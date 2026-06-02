package com.example.lab05danp.data.repository

import com.example.lab05danp.data.model.Product

interface IProductRepository {
    fun getProducts(): List<Product>
}
