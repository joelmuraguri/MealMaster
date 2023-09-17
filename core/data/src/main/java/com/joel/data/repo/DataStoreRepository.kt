package com.joel.data.repo

import android.content.Context
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

}

class DataRepositoryImpl @Inject constructor(
    context: Context
) : DataStoreRepository{

    private object PreferencesKey {
        val onBoardingKey = booleanPreferencesKey(name = "preference_save_completed")
    }

    private val dataStore = context.dataStore


    override suspend fun savePreferenceState(completed: Boolean) {
        dataStore.edit { preferences ->
            preferences[PreferencesKey.onBoardingKey] = completed
        }

    }

    override suspend fun readPreferenceState(): Flow<Boolean> {
        return dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { preferences ->
                val onBoardingState = preferences[PreferencesKey.onBoardingKey] ?: false
                onBoardingState
            }
    }

}