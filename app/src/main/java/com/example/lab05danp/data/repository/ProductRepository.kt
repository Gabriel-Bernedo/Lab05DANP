package com.example.lab05danp.data.repository

import com.example.lab05danp.data.model.Product

class ProductRepository {
    fun getProducts(): List<Product> {
        return listOf(
            Product(1, "Laptop", 1500.0),
            Product(2, "Televisor 4K 55\"", 2500.0),
            Product(3, "Consola de Videojuegos", 1200.0),
            Product(4, "Auriculares Cancelación Ruido", 300.0),
            Product(5, "Cámara Fotográfica Reflex", 4000.0)
        )
    }
}
