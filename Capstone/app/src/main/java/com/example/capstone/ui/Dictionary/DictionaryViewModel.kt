package com.example.capstone.ui.Dictionary

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.capstone.Api.ApiConfig
import com.example.capstone.Api.DictionaryResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class DictionaryViewModel: ViewModel() {
    private val _user = MutableLiveData<List<DictionaryResponse>?>()
    val users: MutableLiveData<List<DictionaryResponse>?> = _user

    fun getUser() {
        val client = ApiConfig.getApiService().getDictionary(path = String(), meaning = String())
        client.enqueue(object : Callback<List<DictionaryResponse>> {
            override fun onResponse(
                call: Call<List<DictionaryResponse>>,
                response: Response<List<DictionaryResponse>>
            ) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        Log.i("TAG", "responBody: ${responseBody}")
                        _user.postValue(responseBody)
                    }
                } else {
                    Log.e("TAG", "onResponse: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<List<DictionaryResponse>>, t: Throwable) {
                Log.e("TAG", "onFailure: ${t.message}")
                _user.postValue(null)
            }
        })
    }
    init {
        getUser()
    }
}