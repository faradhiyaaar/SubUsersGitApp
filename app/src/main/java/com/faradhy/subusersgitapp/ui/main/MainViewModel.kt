package com.faradhy.subusersgitapp.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.faradhy.subusersgitapp.databs.GitUserResponse
import com.faradhy.subusersgitapp.databs.ItemsItem
import com.faradhy.subusersgitapp.databs.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel: ViewModel() {

    private val _listUser = MutableLiveData<List<ItemsItem?>?>()
    val listUser: LiveData<List<ItemsItem?>?> = _listUser

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    companion object{
        private const val TAG = "MainViewModel"
        private const val USER_ID = "fara"
    }

    init {
        findUser(USER_ID)
    }

    fun findUser(query: String){
        _isLoading.value= true
        val client = ApiConfig.getApiService().getUser(query)
        client.enqueue(object : Callback<GitUserResponse> {
            override fun onResponse(
                call: Call<GitUserResponse>,
                response: Response<GitUserResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _listUser.value= response.body()?.items
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<GitUserResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

}