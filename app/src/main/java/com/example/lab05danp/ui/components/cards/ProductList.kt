package com.example.lab05danp.ui.components.cards

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ProductList(
    productos: List<String>
) {
    LazyColumn {
        items(productos) { producto ->
            ProductCard(
                nombre = producto,
                precio = 1500.0
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProductListPreview() {
    ProductList(
        productos = listOf(
            "Televisor 4K 55\"",
            "Consola de Videojuegos",
            "Auriculares Cancelación Ruido",
            "Cámara Fotográfica Reflex"
        )
    )
}
