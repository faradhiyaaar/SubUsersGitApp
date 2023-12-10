package com.faradhy.subusersgitapp.databs

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [UserFavor::class], version = 2, exportSchema = false)
abstract class UserFavorRoomDatabase: RoomDatabase() {
    companion object{
        @Volatile
        var INSTANCE:UserFavorRoomDatabase? = null

        @JvmStatic
        fun getDatabase(context: Context):UserFavorRoomDatabase{
            if (INSTANCE==null){
                synchronized(UserFavorRoomDatabase::class){
                    INSTANCE=Room.databaseBuilder(context.applicationContext,UserFavorRoomDatabase::class.java,"fav_database")
                        .fallbackToDestructiveMigration()
                        .build()

                }
            }
            return INSTANCE as UserFavorRoomDatabase
        }

    }
    abstract fun userFavorDao(): UserFavorDao
}