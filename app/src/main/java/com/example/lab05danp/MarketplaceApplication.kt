package com.example.lab05danp

import android.app.Application
import com.example.lab05danp.di.AppContainer
import com.example.lab05danp.di.DefaultAppContainer

class MarketplaceApplication : Application() {
    /**
     * AppContainer instance used by the rest of classes to obtain dependencies
     */
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}
