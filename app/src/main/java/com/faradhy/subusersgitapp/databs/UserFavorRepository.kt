package com.faradhy.subusersgitapp.databs

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.faradhy.subusersgitapp.utils.AppExecutors

class UserFavorRepository private constructor(
    private val userFavorDao: UserFavorDao,
    private val appExecutors: AppExecutors
) {
    private val result = MediatorLiveData<Result<List<UserFavor>>>()

    fun insertFavor(username:UserFavor){
      appExecutors.diskIO.execute{
          userFavorDao.insertuserFavor(username)
      }
        val localData = userFavorDao.getAlluserFavor()
        result.addSource(localData) { newData: List<UserFavor> ->
            result.value = Result.Success(newData)
        }
    }

    fun getAllFavor(): LiveData<List<UserFavor>> {
        return userFavorDao.getAlluserFavor()
    }

    fun getFavorByUsername(username:String):LiveData<UserFavor> {
        return userFavorDao.getuserFavorByUsername(username)
    }

    fun deleteFavor(userFavor: UserFavor){
        appExecutors.diskIO.execute{
            userFavorDao.deleteuserFavor(userFavor)
        }
    }

    companion object {
        @Volatile
        private var instance: UserFavorRepository? = null
        fun getInstance(
            newsDao: UserFavorDao,
            appExecutors: AppExecutors
        ): UserFavorRepository =
            instance ?: synchronized(this) {
                instance ?: UserFavorRepository(newsDao, appExecutors)
            }.also { instance = it }
    }
}