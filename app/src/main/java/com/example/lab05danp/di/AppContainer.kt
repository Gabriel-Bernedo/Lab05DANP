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

interface AppContainer {
    val userRepository: IUserRepository
    val productRepository: IProductRepository
    val sessionRepository: ISessionRepository
    val cartRepository: ICartRepository
    val orderRepository: IOrderRepository
    val productLogger: IProductLogger
    val sessionManager: ISessionManager
}

class DefaultAppContainer : AppContainer {
    // Implementamos los repositorios como lazy properties para que sean Singletons
    // compartidos y se inicialicen solo cuando se necesiten por primera vez.

    override val userRepository: IUserRepository by lazy {
        UserRepositoryImpl()
    }

    override val productRepository: IProductRepository by lazy {
        ProductRepositoryImpl()
    }

    override val sessionRepository: ISessionRepository by lazy {
        SessionRepositoryImpl()
    }

    override val cartRepository: ICartRepository by lazy {
        CartRepositoryImpl()
    }

    override val orderRepository: IOrderRepository by lazy {
        OrderRepositoryImpl()
    }

    override val productLogger: IProductLogger by lazy {
        ProductLoggerImpl()
    }

    override val sessionManager: ISessionManager by lazy {
        SessionManagerImpl(
            sessionRepository = sessionRepository,
            productLogger = productLogger
        )
    }
}
