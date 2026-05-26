package com.example.lab05danp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lab05danp.data.model.Product

@Composable
fun ProductCard(
    product: Product,
    onDetailsClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .background(Color(0xFFFFF59D))
            .border(1.dp, Color.Gray)
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Image Placeholder
        Box(
            modifier = Modifier
                .size(60.dp)
                .background(Color.White)
                .border(1.dp, Color.Gray)
        )
        
        Spacer(modifier = Modifier.width(16.dp))
        
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(text = product.name, color = Color(0xFF1565C0), fontSize = 16.sp)
            if (product.discountPrice != null) {
                Text(
                    text = "${product.originalPrice.toInt()} USD",
                    color = Color.Red,
                    fontSize = 12.sp,
                    textDecoration = TextDecoration.LineThrough
                )
                Text(
                    text = "${product.discountPrice.toInt()} USD",
                    color = Color.Red,
                    fontSize = 14.sp
                )
            } else {
                Text(
                    text = "${product.originalPrice.toInt()} USD",
                    color = Color(0xFF1565C0),
                    fontSize = 14.sp
                )
            }
        }
        
        Button(
            onClick = onDetailsClick,
            colors = ButtonDefaults.buttonColors(containerColor = Color.White),
            modifier = Modifier.border(1.dp, Color(0xFF1565C0))
        ) {
            Text("Ver detalles", color = Color(0xFF1565C0), fontSize = 12.sp)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProductCardPreview() {
    ProductCard(
        product = Product(1, "Chip MRC 500", 400.0, 300.0),
        onDetailsClick = {}
    )
}
