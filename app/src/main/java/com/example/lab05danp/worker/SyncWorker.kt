package com.example.lab05danp.worker

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import kotlinx.coroutines.delay

class SyncWorker(
    appContext: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork(): Result {
        Log.d("SyncWorker", "Iniciando sincronización de datos en Lab05DANP...")
        
        // Simular sincronización de órdenes o productos
        delay(3000)
        
        Log.d("SyncWorker", "Sincronización de Lab05DANP completada con éxito.")
        
        return Result.success()
    }
}
