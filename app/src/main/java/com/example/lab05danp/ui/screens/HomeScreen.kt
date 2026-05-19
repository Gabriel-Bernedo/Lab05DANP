package com.example.lab05danp.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lab05danp.ui.components.buttons.PrimaryButton
import com.example.lab05danp.ui.components.cards.ProductList
import com.example.lab05danp.ui.components.inputs.SearchBar
import com.example.lab05danp.ui.navigation.AppBottomBar
import com.example.lab05danp.ui.navigation.AppTopBar

@Composable
fun HomeScreen() {
    var texto by remember {
        mutableStateOf(value = "")
    }

    val productos = listOf(
        "Laptop",
        "Mouse",
        "Teclado",
        "Monitor"
    )

    Scaffold(
        topBar = { AppTopBar() },
        bottomBar = {
            AppBottomBar()
        },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(paddingValues = innerPadding)
                .padding(horizontal = 16.dp)
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                SearchBar(
                    value = texto,
                    onValueChange = { texto = it },
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(8.dp))
                PrimaryButton(
                    text = "Buscar",
                    modifier = Modifier.wrapContentWidth(),
                    onClick = {
                        // Acción de búsqueda
                    }
                )
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            ProductList(
                productos = productos
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}
