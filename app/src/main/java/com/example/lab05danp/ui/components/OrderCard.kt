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
import androidx.compose.material3.MaterialTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.draw.shadow

@Composable
fun OrderCard(
    order: Order,
    onDetailsClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .shadow(elevation = 6.dp, shape = RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(16.dp))
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text("Fecha: ${order.date}", color = MaterialTheme.colorScheme.primary, fontSize = 14.sp)
            Text(order.status, color = if (order.status == "PENDIENTE") MaterialTheme.colorScheme.outline else MaterialTheme.colorScheme.primary, fontSize = 10.sp, modifier = Modifier.border(1.dp, MaterialTheme.colorScheme.outline).padding(2.dp).background(MaterialTheme.colorScheme.surface))
            Spacer(modifier = Modifier.height(4.dp))
            Text("Total: ${order.totalAmount.toInt()} USD", color = MaterialTheme.colorScheme.primary, fontSize = 14.sp, fontWeight = FontWeight.Bold)
        }
        
        Button(
            onClick = onDetailsClick,
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.surface),
            modifier = Modifier.shadow(4.dp, RoundedCornerShape(8.dp))
        ) {
            Text("Ver\ndetalles", color = MaterialTheme.colorScheme.primary, fontSize = 12.sp)
        }
    }
}
