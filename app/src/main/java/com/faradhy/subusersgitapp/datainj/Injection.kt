package com.faradhy.subusersgitapp.datainj

import android.content.Context
import com.faradhy.subusersgitapp.utils.AppExecutors
import com.faradhy.subusersgitapp.databs.UserFavorRepository
import com.faradhy.subusersgitapp.databs.UserFavorRoomDatabase

object Injection {
    fun provideRepository(context: Context): UserFavorRepository {
        val database = UserFavorRoomDatabase.getDatabase(context)
        val dao = database.userFavorDao()
        val appExecutors = AppExecutors()
        return UserFavorRepository.getInstance(dao, appExecutors)
    }
}