package com.faradhy.subusersgitapp.ui.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.faradhy.subusersgitapp.databs.UserDetailRespons
import com.faradhy.subusersgitapp.databs.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel:ViewModel() {

    private val _user = MutableLiveData<UserDetailRespons>()
    val user: LiveData<UserDetailRespons>
        get() = _user

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    fun setUserDetail(username:String){
        _isLoading.value=true
        ApiConfig.getApiService().getDetailUser(username)
            .enqueue(object : Callback<UserDetailRespons> {
                override fun onResponse(
                    call: Call<UserDetailRespons>,
                    response: Response<UserDetailRespons>
                ) {
                    _isLoading.value = false
                    if (response.isSuccessful) {
                        _user.value = response.body()
                    } else {
                        Log.e("Failure", "onFailure: ${response.message()}")
                    }
                }
                override fun onFailure(call: Call<UserDetailRespons>, t: Throwable) {
                    Log.d("Failure", "onFailure: ${t.message.toString()}")
                    _isLoading.value = false
                }
            })
    }
    fun getUserDetail(): LiveData<UserDetailRespons>{
        return user
    }

}