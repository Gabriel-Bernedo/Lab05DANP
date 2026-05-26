package com.example.lab05danp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lab05danp.data.model.Order

@Composable
fun OrderCard(
    order: Order,
    onDetailsClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .background(Color(0xFFFFF59D))
            .border(1.dp, Color.Gray)
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text("Fecha: ${order.date}", color = Color(0xFF1565C0), fontSize = 14.sp)
            Text(order.status, color = if (order.status == "PENDIENTE") Color.Gray else Color(0xFF64B5F6), fontSize = 10.sp, modifier = Modifier.border(1.dp, Color.Gray).padding(2.dp).background(Color.White))
            Spacer(modifier = Modifier.height(4.dp))
            Text("Total: ${order.totalAmount.toInt()} USD", color = Color(0xFF1565C0), fontSize = 14.sp, fontWeight = FontWeight.Bold)
        }
        
        Button(
            onClick = onDetailsClick,
            colors = ButtonDefaults.buttonColors(containerColor = Color.White),
            modifier = Modifier.border(1.dp, Color(0xFF1565C0))
        ) {
            Text("Ver\ndetalles", color = Color(0xFF1565C0), fontSize = 12.sp)
        }
    }
}
