package com.internsathi.bookfinder.data.local

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.core.IOException
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserPreferencesRepository @Inject constructor(
    private val dataStore: DataStore<Preferences>
) {
    private companion object {
        val IS_LIGHT_MODE = booleanPreferencesKey("is_light_mode")
        const val TAG = "UserPreferencesRepo"
    }

    val isLightMode: Flow<Boolean> = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                Log.e(TAG, "Error reading preferences.", exception)
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferences -> preferences[IS_LIGHT_MODE] != false }

    suspend fun saveThemePreferences(isLightMode: Boolean) {
        dataStore.edit { preferences ->
            preferences[IS_LIGHT_MODE] = isLightMode
        }
    }
}
