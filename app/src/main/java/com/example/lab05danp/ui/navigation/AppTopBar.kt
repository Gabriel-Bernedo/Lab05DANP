package com.example.lab05danp.ui.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.material3.MaterialTheme

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.ui.platform.LocalContext
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.example.lab05danp.worker.SyncWorker

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar() {
    val context = LocalContext.current
    TopAppBar(
        title = {
            Text("MARKETPLACE", color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold)
        },
        actions = {
            IconButton(onClick = {
                val syncWorkRequest = OneTimeWorkRequestBuilder<SyncWorker>().build()
                WorkManager.getInstance(context).enqueue(syncWorkRequest)
            }) {
                Icon(
                    imageVector = Icons.Default.Refresh,
                    contentDescription = "Sincronizar"
                )
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun AppTopBarPreview() {
    AppTopBar()
}
