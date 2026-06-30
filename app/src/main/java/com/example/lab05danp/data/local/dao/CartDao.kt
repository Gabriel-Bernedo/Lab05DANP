package com.example.lab05danp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.lab05danp.data.local.entity.CartItemEntity
import com.example.lab05danp.data.local.entity.CartItemWithProduct
import kotlinx.coroutines.flow.Flow

@Dao
interface CartDao {
    @Transaction
    @Query("SELECT * FROM cart_items")
    fun getCartItems(): Flow<List<CartItemWithProduct>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCartItems(items: List<CartItemEntity>)

    @Query("SELECT * FROM cart_items WHERE productId = :productId LIMIT 1")
    suspend fun getCartItem(productId: Int): CartItemEntity?

    @Query("DELETE FROM cart_items")
    suspend fun clearCart()
}
