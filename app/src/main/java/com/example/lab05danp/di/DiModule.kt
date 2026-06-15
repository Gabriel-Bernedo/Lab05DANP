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
    fun provideProductRepository(): IProductRepository {
        return ProductRepositoryImpl()
    }

    @Provides
    @Singleton
    fun provideSessionRepository(): ISessionRepository {
        return SessionRepositoryImpl()
    }

    @Provides
    @Singleton
    fun provideCartRepository(): ICartRepository {
        return CartRepositoryImpl()
    }

    @Provides
    @Singleton
    fun provideOrderRepository(): IOrderRepository {
        return OrderRepositoryImpl()
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
