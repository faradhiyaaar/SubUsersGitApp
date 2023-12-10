package com.faradhy.subusersgitapp.ui.settings

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ThemeModeSetViewModel(private val themeModePreference: ThemeModeSetPreference):ViewModel() {
    fun getThemeMode(): LiveData<Boolean>{
        return themeModePreference.getThemeMode().asLiveData()
    }

    fun setThemeMode(isDarkModeActive:Boolean){
        viewModelScope.launch {
            themeModePreference.saveModeSetting(isDarkModeActive)
        }
    }
}