package com.example.lab05danp.data.repository

import com.example.lab05danp.data.model.Order
import com.example.lab05danp.data.model.Product
import com.example.lab05danp.data.model.User


object MockData {
    val categories = listOf("Electronicos", "Sensores", "Actuadores", "Motores")
    
    val products = listOf(
        Product(1, "Chip MRC 500", 400.0, null, category = "Electronicos", description = "Microcontrolador avanzado de alto rendimiento."),
        Product(2, "Chip MRC 400", 400.0, 300.0, category = "Electronicos", description = "Versión económica del MRC 500."),
        Product(3, "Chip MRC 500", 400.0, null, category = "Electronicos", description = "Microcontrolador avanzado de alto rendimiento."),
        Product(4, "Chip MRC 500", 400.0, null, category = "Electronicos", description = "Microcontrolador avanzado de alto rendimiento."),
        Product(5, "Chip MRC 500", 400.0, null, category = "Electronicos", description = "Microcontrolador avanzado de alto rendimiento.")
    )
    
    val orders = listOf(
        Order(1, "HOY", "PENDIENTE", 12540.0),
        Order(2, "AYER", "ENTREGADO", 12540.0),
        Order(3, "12/05/2025", "ENTREGADO", 12540.0),
        Order(4, "12/05/2025", "ENTREGADO", 12540.0),
        Order(5, "12/05/2025", "ENTREGADO", 12540.0)
    )
}
