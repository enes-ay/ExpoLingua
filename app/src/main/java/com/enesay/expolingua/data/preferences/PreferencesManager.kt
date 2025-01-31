package com.enesay.expolingua.data.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "widget_prefs")

class PreferencesManager(private val context: Context) {
    private val currentWordIndexKey = intPreferencesKey("current_word_index")

    suspend fun getCurrentWordIndex(): Int {
        return context.dataStore.data.map { preferences ->
            preferences[currentWordIndexKey] ?: 0
        }.first()
    }

    suspend fun incrementWordIndex() {
        context.dataStore.edit { preferences ->
            val currentIndex = preferences[currentWordIndexKey] ?: 0
            preferences[currentWordIndexKey] = currentIndex + 1
        }
    }
} 