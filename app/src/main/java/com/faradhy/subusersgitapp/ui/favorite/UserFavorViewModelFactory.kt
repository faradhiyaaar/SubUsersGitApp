package com.faradhy.subusersgitapp.ui.favorite

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.faradhy.subusersgitapp.databs.UserFavorRepository
import com.faradhy.subusersgitapp.datainj.Injection

class UserFavorViewModelFactory private constructor(private val repository: UserFavorRepository) :
    ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserFavorViewModel::class.java)) {
            return UserFavorViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }

    companion object {
        @Volatile
        private var instance: UserFavorViewModelFactory? = null
        fun getInstance(context: Context): UserFavorViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: UserFavorViewModelFactory(Injection.provideRepository(context))
            }.also { instance = it }
    }
}