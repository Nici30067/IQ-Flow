package com.example.iq_flow_one_zero.ui

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.example.iq_flow_one_zero.data.AppContainer
import com.example.iq_flow_one_zero.data.AppDataContainer
import com.example.iq_flow_one_zero.data.UserPreferencesRepository
import androidx.datastore.preferences.preferencesDataStore



private const val DESIGN_PREFERENCE_NAME = "design_preferences"
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
    DESIGN_PREFERENCE_NAME
)
class FlashcardApplication: Application() {
    lateinit var userPreferencesRepository: UserPreferencesRepository
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
        userPreferencesRepository = UserPreferencesRepository(dataStore)
    }
}