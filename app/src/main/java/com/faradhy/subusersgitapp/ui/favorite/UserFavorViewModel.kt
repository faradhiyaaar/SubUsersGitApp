package com.faradhy.subusersgitapp.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.faradhy.subusersgitapp.databs.UserFavor
import com.faradhy.subusersgitapp.databs.UserFavorRepository

class UserFavorViewModel(private val repository: UserFavorRepository) : ViewModel() {

    fun getAllFavorUser(): LiveData<List<UserFavor>> = repository.getAllFavor()

    fun getFavorUserByUsername(username: String): LiveData<UserFavor> = repository.getFavorByUsername(username)

    fun insertUserFavor(userFavor: UserFavor) {
        repository.insertFavor(userFavor)
    }

    fun deleteUserFavor(userFavor: UserFavor) {
        repository.deleteFavor(userFavor)
    }
}