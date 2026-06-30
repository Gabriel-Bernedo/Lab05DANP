package com.example.lab05danp.di

import com.example.lab05danp.data.repository.CartRepositoryImpl
import com.example.lab05danp.data.repository.ICartRepository
import com.example.lab05danp.data.repository.IOrderRepository
import com.example.lab05danp.data.repository.IProductRepository
import com.example.lab05danp.data.repository.ISessionRepository
import com.example.lab05danp.data.repository.IUserRepository
import com.example.lab05danp.data.repository.OrderRepositoryImpl
import com.example.lab05danp.data.repository.ProductRepositoryImpl
import com.example.lab05danp.data.repository.SessionRepositoryImpl
import com.example.lab05danp.data.repository.UserRepositoryImpl
import com.example.lab05danp.domain.logger.IProductLogger
import com.example.lab05danp.domain.logger.ProductLoggerImpl
import com.example.lab05danp.domain.session.ISessionManager
import com.example.lab05danp.domain.session.SessionManagerImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DiModule {

    @Provides
    @Singleton
    fun provideUserRepository(): IUserRepository {
        return UserRepositoryImpl()
    }

    @Provides
    @Singleton
    fun provideProductRepository(productDao: com.example.lab05danp.data.local.dao.ProductDao): IProductRepository {
        return ProductRepositoryImpl(productDao)
    }

    @Provides
    @Singleton
    fun provideSessionRepository(userDao: com.example.lab05danp.data.local.dao.UserDao): ISessionRepository {
        return SessionRepositoryImpl(userDao)
    }

    @Provides
    @Singleton
    fun provideCartRepository(cartDao: com.example.lab05danp.data.local.dao.CartDao): ICartRepository {
        return CartRepositoryImpl(cartDao)
    }

    @Provides
    @Singleton
    fun provideOrderRepository(orderDao: com.example.lab05danp.data.local.dao.OrderDao): IOrderRepository {
        return OrderRepositoryImpl(orderDao)
    }

    @Provides
    @Singleton
    fun provideProductLogger(): IProductLogger {
        return ProductLoggerImpl()
    }

    @Provides
    @Singleton
    fun provideSessionManager(
        sessionRepository: ISessionRepository,
        productLogger: IProductLogger
    ): ISessionManager {
        return SessionManagerImpl(sessionRepository, productLogger)
    }
}
