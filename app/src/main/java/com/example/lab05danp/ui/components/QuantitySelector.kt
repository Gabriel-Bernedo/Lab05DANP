package com.example.lab05danp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun QuantitySelector(
    quantity: Int,
    onIncrease: () -> Unit,
    onDecrease: () -> Unit
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = "+",
            color = Color(0xFF1565C0),
            fontSize = 24.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .size(36.dp)
                .clip(CircleShape)
                .border(1.dp, Color.Gray, CircleShape)
                .background(Color.White)
                .clickable { onIncrease() }
                .padding(top = 2.dp)
        )
        Text(
            text = quantity.toString(),
            color = Color(0xFF1565C0),
            fontSize = 20.sp,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Text(
            text = "-",
            color = Color(0xFF1565C0),
            fontSize = 24.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .size(36.dp)
                .clip(CircleShape)
                .border(1.dp, Color.Gray, CircleShape)
                .background(Color.White)
                .clickable { if (quantity > 0) onDecrease() }
                .padding(top = 2.dp)
        )
    }
}
