package com.example.lab05danp.data.repository

import com.example.lab05danp.data.model.Product
import com.example.lab05danp.data.local.dao.ProductDao
import com.example.lab05danp.data.local.entity.ProductEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val productDao: ProductDao
) : IProductRepository {

    override fun getProducts(): Flow<List<Product>> {
        return productDao.getProducts().map { entities ->
            entities.map { Product(it) }
        }
    }

    override suspend fun syncProducts() {
        try {
            val remoteProducts = com.example.lab05danp.data.remote.RetrofitClient.api.getProducts()
            val entities = remoteProducts.map {
                ProductEntity(
                    id = it.id,
                    title = it.title,
                    price = it.price,
                    description = it.description,
                    image = it.image,
                    category = it.category,
                    lastUpdated = System.currentTimeMillis()
                )
            }
            productDao.insertProducts(entities)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
