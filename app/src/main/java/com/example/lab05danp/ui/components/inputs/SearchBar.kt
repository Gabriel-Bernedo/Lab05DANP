package com.example.lab05danp.ui.components.inputs

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun SearchBar(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        label = {
            Text("Buscar producto")
        }
    )
}

@Preview(showBackground = true)
@Composable
fun SearchBarPreview() {
    var text by remember { mutableStateOf("") }
    SearchBar(
        value = text,
        onValueChange = { text = it },
        modifier = Modifier.fillMaxWidth()
    )
}
