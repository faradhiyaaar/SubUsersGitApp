package com.faradhy.subusersgitapp.databs

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserFavorDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertuserFavor(userFavor: UserFavor)

    @Delete
    fun deleteuserFavor(userFavor: UserFavor)

    @Query("SELECT * FROM UserFavor ORDER BY username DESC")
    fun getAlluserFavor(): LiveData<List<UserFavor>>

    @Query("SELECT * FROM userfavor WHERE userfavor.username = :username")
    fun getuserFavorByUsername(username: String): LiveData<UserFavor>




}