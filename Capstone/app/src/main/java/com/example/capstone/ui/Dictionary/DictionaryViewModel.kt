package com.example.capstone.ui.Dictionary

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.capstone.Api.ApiConfig
import com.example.capstone.Api.DictionaryApiResponse
import com.example.capstone.Api.DictionaryResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class DictionaryViewModel: ViewModel() {
    private val _user = MutableLiveData<List<DictionaryResponse>?>()
    val users: MutableLiveData<List<DictionaryResponse>?> = _user

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading
    init {
        getUser()
    }
    fun getUser() {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getDictionary()
        client.enqueue(object : Callback<DictionaryApiResponse> {
            override fun onResponse(
                call: Call<DictionaryApiResponse>,
                response: Response<DictionaryApiResponse>
            ) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null && responseBody.data != null) {
                        responseBody.data!!.forEach { dictionaryResponse ->
                            Log.i("TAG", "Dictionary Entry: $dictionaryResponse")
                        }
                        _user.postValue(responseBody.data)
                        _isLoading.value = false
                    } else {
                        Log.e("TAG", "Response body is null")
                        _user.postValue(null)
                        _isLoading.value = false
                    }
                } else {
                    Log.e("TAG", "onResponse: ${response.message()}")
                    _isLoading.value = false
                }
            }

            override fun onFailure(call: Call<DictionaryApiResponse>, t: Throwable) {
                Log.e("TAG", "onFailure: ${t.message}")
                _user.postValue(null)
                _isLoading.value = false
            }
        })
    }
}