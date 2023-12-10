package com.faradhy.subusersgitapp.ui.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ThemeModeSetViewModelFactory(private val themeModePreference: ThemeModeSetPreference):ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ThemeModeSetViewModel::class.java)){
            return ThemeModeSetViewModel(themeModePreference) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")

    }
}