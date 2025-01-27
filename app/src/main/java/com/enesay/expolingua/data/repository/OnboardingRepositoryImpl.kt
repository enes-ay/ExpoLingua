package com.enesay.expolingua.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import com.enesay.expolingua.domain.repository.OnboardingRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class OnboardingRepositoryImpl @Inject constructor(private val dataStore: DataStore<Preferences>) :
    OnboardingRepository {
    companion object {
        private val ONBOARDING_COMPLETE_KEY = booleanPreferencesKey("onboarding_complete")
    }

    override suspend fun setOnboardingComplete(isComplete: Boolean) {
        dataStore.edit { preferences ->
            preferences[ONBOARDING_COMPLETE_KEY] = isComplete
        }
    }

    override suspend fun isOnboardingComplete(): Boolean {
        return dataStore.data.map { preferences ->
            preferences[ONBOARDING_COMPLETE_KEY] ?: false
        }.first()
    }
}