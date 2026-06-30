package com.example.lab05danp.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.lab05danp.data.local.entity.ProductEntity
import com.example.lab05danp.data.local.entity.UserEntity
import com.example.lab05danp.data.local.entity.CartItemEntity
import com.example.lab05danp.data.local.entity.OrderEntity
import com.example.lab05danp.data.local.entity.OrderItemEntity
import com.example.lab05danp.data.local.dao.ProductDao
import com.example.lab05danp.data.local.dao.UserDao
import com.example.lab05danp.data.local.dao.CartDao
import com.example.lab05danp.data.local.dao.OrderDao

@Database(
    entities = [
        ProductEntity::class, 
        UserEntity::class, 
        CartItemEntity::class,
        OrderEntity::class,
        OrderItemEntity::class
    ],
    version = 3,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
    abstract fun userDao(): UserDao
    abstract fun cartDao(): CartDao
    abstract fun orderDao(): OrderDao
}
