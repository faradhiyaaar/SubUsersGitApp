package com.faradhy.subusersgitapp.databs.retrofit

import com.faradhy.subusersgitapp.databs.UserDetailRespons
import com.faradhy.subusersgitapp.databs.GitUserResponse
import com.faradhy.subusersgitapp.databs.ItemsItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.*

interface ApiService {
    @GET("search/users")
    fun getUser(@Query("q") query: String): Call<GitUserResponse>

    @GET("users/{username}")
    fun getDetailUser(@Path("username") username: String): Call<UserDetailRespons>

    @GET("users/{username}/followers")
    fun getFollowers(@Path("username") username: String): Call<List<ItemsItem>>

    @GET("users/{username}/following")
    fun getFollowing(@Path("username") username: String): Call<List<ItemsItem>>

}