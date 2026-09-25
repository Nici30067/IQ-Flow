package com.example.iq_flow_one_zero.ui

import android.app.Application
import com.example.iq_flow_one_zero.data.AppContainer
import com.example.iq_flow_one_zero.data.AppDataContainer

class FlashcardApplication: Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}