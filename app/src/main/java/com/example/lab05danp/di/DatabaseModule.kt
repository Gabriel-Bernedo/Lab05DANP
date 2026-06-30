package com.example.lab05danp.di

import android.content.Context
import androidx.room.Room
import com.example.lab05danp.data.local.database.AppDatabase
import com.example.lab05danp.data.local.dao.ProductDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "ecommerce.db"
        )
        .fallbackToDestructiveMigration()
        .build()
    }

    @Provides
    fun provideProductDao(database: AppDatabase): ProductDao {
        return database.productDao()
    }

    @Provides
    fun provideUserDao(database: AppDatabase): com.example.lab05danp.data.local.dao.UserDao {
        return database.userDao()
    }

    @Provides
    fun provideCartDao(database: AppDatabase): com.example.lab05danp.data.local.dao.CartDao {
        return database.cartDao()
    }

    @Provides
    fun provideOrderDao(database: AppDatabase): com.example.lab05danp.data.local.dao.OrderDao {
        return database.orderDao()
    }
}
