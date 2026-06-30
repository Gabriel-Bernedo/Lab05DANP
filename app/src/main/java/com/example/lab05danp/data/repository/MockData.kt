package com.example.lab05danp.data.repository

import com.example.lab05danp.data.model.Order
import com.example.lab05danp.data.model.Product
import com.example.lab05danp.data.model.User


object MockData {
    val categories = listOf("Electronicos", "Sensores", "Actuadores", "Motores")
    
    val products = listOf(
        Product(1, "Chip MRC 500", 400.0, "Microcontrolador avanzado de alto rendimiento.", "", "Electronicos"),
        Product(2, "Chip MRC 400", 300.0, "Versión económica del MRC 500.", "", "Electronicos"),
        Product(3, "Chip MRC 500", 400.0, "Microcontrolador avanzado de alto rendimiento.", "", "Electronicos"),
        Product(4, "Chip MRC 500", 400.0, "Microcontrolador avanzado de alto rendimiento.", "", "Electronicos"),
        Product(5, "Chip MRC 500", 400.0, "Microcontrolador avanzado de alto rendimiento.", "", "Electronicos")
    )
    
    val orders = listOf(
        Order(1, "HOY", "PENDIENTE", 12540.0),
        Order(2, "AYER", "ENTREGADO", 12540.0),
        Order(3, "12/05/2025", "ENTREGADO", 12540.0),
        Order(4, "12/05/2025", "ENTREGADO", 12540.0),
        Order(5, "12/05/2025", "ENTREGADO", 12540.0)
    )
}
