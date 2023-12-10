package com.faradhy.subusersgitapp.ui.settings

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore : DataStore<Preferences> by preferencesDataStore("setting")

class ThemeModeSetPreference (private val dataStore:DataStore<Preferences>) {

    private val MODE_KEY = booleanPreferencesKey("mode_setting")

    fun getThemeMode():Flow<Boolean>{
        return dataStore.data.map { preferences->
            preferences[MODE_KEY]?:false
        }
    }

    suspend fun saveModeSetting(isDarkModeActive:Boolean){
        dataStore.edit { preferences->
            preferences[MODE_KEY]= isDarkModeActive
        }
    }

    companion object{
        @Volatile
        private var INSTANCE: ThemeModeSetPreference?=null

        fun getInstance(ds:DataStore<Preferences>):ThemeModeSetPreference{
            return INSTANCE?: synchronized(this){
                val instance = ThemeModeSetPreference(ds)
                INSTANCE =instance
                instance
            }
        }
    }
}