package com.faradhy.subusersgitapp.databs

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "UserFavor")
class UserFavor(

    @field:ColumnInfo(name = "username")
    @field:PrimaryKey
    var username: String="",

    @field:ColumnInfo(name = "urlAvatar")
    var urlAvatar: String? = null,
)