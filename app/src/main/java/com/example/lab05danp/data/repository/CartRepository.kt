package com.example.lab05danp.data.repository

import com.example.lab05danp.data.model.CartItem
import com.example.lab05danp.data.model.Product
import com.example.lab05danp.data.local.dao.CartDao
import com.example.lab05danp.data.local.entity.CartItemEntity
import com.example.lab05danp.data.remote.RetrofitClient
import com.example.lab05danp.data.remote.dto.CartItemRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

interface ICartRepository {
    val cartItems: Flow<List<CartItem>>
    suspend fun fetchCart()
    suspend fun addToCart(product: Product, quantity: Int)
    suspend fun clearCart()
}

class CartRepositoryImpl @Inject constructor(
    private val cartDao: CartDao
) : ICartRepository {
    
    override val cartItems: Flow<List<CartItem>> = cartDao.getCartItems().map { items ->
        items.map { itemWithProduct ->
            CartItem(
                product = Product(itemWithProduct.product),
                quantity = itemWithProduct.cartItem.quantity
            )
        }
    }

    override suspend fun fetchCart() {
        try {
            val response = RetrofitClient.api.getCart()
            val entities = response.items.map { dto ->
                CartItemEntity(
                    productId = dto.productId,
                    quantity = dto.quantity,
                    lastUpdated = System.currentTimeMillis()
                )
            }
            cartDao.insertCartItems(entities)
        } catch (e: Exception) {
            e.printStackTrace()
            // Podríamos manejar errores de red o token expirado aquí
        }
    }

    override suspend fun addToCart(product: Product, quantity: Int) {
        // Optimistic local update
        val existingItem = cartDao.getCartItem(product.id)
        val newQuantity = (existingItem?.quantity ?: 0) + quantity
        val localItem = CartItemEntity(
            productId = product.id,
            quantity = newQuantity,
            lastUpdated = System.currentTimeMillis()
        )
        cartDao.insertCartItems(listOf(localItem))

        try {
            val response = RetrofitClient.api.addToCart(CartItemRequest(product.id, quantity))
            val entities = response.items.map { dto ->
                CartItemEntity(
                    productId = dto.productId,
                    quantity = dto.quantity,
                    lastUpdated = System.currentTimeMillis()
                )
            }
            cartDao.insertCartItems(entities)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override suspend fun clearCart() {
        // Optimistic local clear
        cartDao.clearCart()

        try {
            RetrofitClient.api.clearCart()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
