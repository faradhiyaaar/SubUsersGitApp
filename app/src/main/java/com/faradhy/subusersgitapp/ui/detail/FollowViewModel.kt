package com.faradhy.subusersgitapp.ui.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.faradhy.subusersgitapp.databs.ItemsItem
import com.faradhy.subusersgitapp.databs.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowViewModel:ViewModel() {

    private val _listFollowers = MutableLiveData<List<ItemsItem>>()

    private val _following = MutableLiveData<List<ItemsItem>>()

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    companion object{
        private const val TAG = "FollowViewModel"
    }

    fun setListFollowers(username: String){
        _isLoading.value= true
        ApiConfig.getApiService().getFollowers(username)
            .enqueue(object : Callback<List<ItemsItem>> {

            override fun onResponse(
                call: Call<List<ItemsItem>>,
                response: Response<List<ItemsItem>>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _listFollowers.postValue(response.body())
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<List<ItemsItem>>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

    fun setListFollowing(username: String){
        _isLoading.value= true
        ApiConfig.getApiService().getFollowing(username)
            .enqueue(object : Callback<List<ItemsItem>> {

            override fun onResponse(
                call: Call<List<ItemsItem>>,
                response: Response<List<ItemsItem>>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _following.postValue(response.body())
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<List<ItemsItem>>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

    fun getListFollowers(): LiveData<List<ItemsItem>>{
        return _listFollowers
    }

    fun getListFollowing(): LiveData<List<ItemsItem>>{
        return _following
    }


}