package com.joel.data.repo

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_preference")


interface DataStoreRepository {

    suspend fun savePreferenceState(completed : Boolean)

    suspend fun readPreferenceState() : Flow<Boolean>
    suspend fun saveOnboardingState(completed : Boolean)
    suspend fun readOnboardingState() : Flow<Boolean>

}

class DataRepositoryImpl @Inject constructor(
    context: Context
) : DataStoreRepository{

    private object PreferencesKey {
        val preferenceKey = booleanPreferencesKey(name = "preference_save_completed")
        val onboardingKey = booleanPreferencesKey(name = "onboarding_save_completed")
    }

    private val dataStore = context.dataStore

    override suspend fun savePreferenceState(completed: Boolean) {
        dataStore.edit { preferences ->
            preferences[PreferencesKey.preferenceKey] = completed
        }

    }

    override suspend fun readPreferenceState(): Flow<Boolean> {
        Log.d("DataRepositoryImpl", "Reading preference state...")
        return dataStore.data
            .catch { exception ->
                Log.e("DataRepositoryImpl", "Error reading preference state: $exception")
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { preferences ->
                val onPreferenceState = preferences[PreferencesKey.preferenceKey] ?: false
                Log.d("DataRepositoryImpl", "Preference state: $onPreferenceState")
                onPreferenceState
            }
    }

    override suspend fun saveOnboardingState(completed: Boolean) {
        dataStore.edit { preferences ->
            preferences[PreferencesKey.onboardingKey] = completed
        }
    }

    override suspend fun readOnboardingState(): Flow<Boolean> {
        return dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { preferences ->
                val onBoardingState = preferences[PreferencesKey.onboardingKey] ?: false
                onBoardingState
            }
    }

}