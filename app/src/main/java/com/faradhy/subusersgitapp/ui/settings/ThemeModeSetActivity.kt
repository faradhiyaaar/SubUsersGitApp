package com.faradhy.subusersgitapp.ui.settings

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CompoundButton
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import com.faradhy.subusersgitapp.R
import com.google.android.material.switchmaterial.SwitchMaterial

class ThemeModeSetActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_theme_set)

        val switchTheme = findViewById<SwitchMaterial>(R.id.switch_theme)

        val preferences = ThemeModeSetPreference.getInstance(application.dataStore)
        val themeModeViewModel=ViewModelProvider(this, ThemeModeSetViewModelFactory(preferences)).get(
            ThemeModeSetViewModel::class.java
        )

        themeModeViewModel.getThemeMode().observe(this){ isDarkModeActive:Boolean->
            if (isDarkModeActive){
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                switchTheme.isChecked=true
            }else{
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                switchTheme.isChecked=false
            }
        }

        switchTheme.setOnCheckedChangeListener{ _:CompoundButton?, isCheked:Boolean->
            themeModeViewModel.setThemeMode(isCheked)

        }
    }
}