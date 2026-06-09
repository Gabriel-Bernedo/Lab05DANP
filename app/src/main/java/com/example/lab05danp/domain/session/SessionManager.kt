package com.example.lab05danp.domain.session

import android.util.Log
import com.example.lab05danp.data.model.Product
import com.example.lab05danp.data.repository.ISessionRepository
import com.example.lab05danp.domain.logger.IProductLogger

interface ISessionManager {
    fun recordProductVisit(product: Product)
}

class SessionManagerImpl(
    private val sessionRepository: ISessionRepository,
    private val productLogger: IProductLogger
) : ISessionManager {

    override fun recordProductVisit(product: Product) {
        val currentUser = sessionRepository.currentUser.value
        val userName = currentUser?.name ?: "Usuario Anónimo"
        
        // Registrar en el Logger
        productLogger.logProductVisit(product.id.toString(), product.name)
        
        // Imprimir el log en consola
        Log.d("SessionManager", "$userName visitó el producto: ${product.name}")
        
        // Opcional: Imprimir todos los productos más visitados para verificación
        val topVisited = productLogger.getMostVisitedProducts()
        Log.d("SessionManager", "Top productos visitados: $topVisited")
    }
}
