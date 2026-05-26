package com.example.lab05danp.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SearchBarRow(
    query: String,
    onQueryChange: (String) -> Unit,
    onSearch: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        BasicTextField(
            value = query,
            onValueChange = onQueryChange,
            modifier = Modifier
                .weight(1f)
                .border(1.dp, Color.Gray)
                .padding(12.dp),
            decorationBox = { innerTextField ->
                if (query.isEmpty()) {
                    Text("Buscar un Producto", color = Color.Gray, fontSize = 14.sp)
                }
                innerTextField()
            }
        )
        Spacer(modifier = Modifier.width(8.dp))
        Button(
            onClick = onSearch,
            colors = ButtonDefaults.buttonColors(containerColor = Color.White),
            modifier = Modifier.border(1.dp, Color(0xFF1565C0))
        ) {
            Text("Buscar", color = Color(0xFF1565C0))
        }
    }
}
