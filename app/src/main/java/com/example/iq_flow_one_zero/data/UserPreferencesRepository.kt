package com.example.iq_flow_one_zero.data

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

class UserPreferencesRepository(private val dataStore: DataStore<Preferences>) {
    val isDarkMode: Flow<Boolean> = dataStore.data.catch {
        if(it is IOException){
            Log.d(TAG, "fehler beim pref reading ", it)
        } else{
            throw it
        }
    }.map { preferences -> preferences[IS_DARK_MODE]?: true }
    companion object{
        val IS_DARK_MODE = booleanPreferencesKey("is_dark_mode")
        const val TAG = "tagForUserPrefRepo"
    }
    suspend fun saveDesignMode(isDarkMode: Boolean){
        dataStore.edit {preferences ->
            preferences[IS_DARK_MODE] = isDarkMode
        }
    }
}