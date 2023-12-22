package com.example.capstone.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.capstone.data.homeApi.ApiConfig
import com.example.capstone.data.response.HomeResponse
import com.example.capstone.data.response.TranslateResponse
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.Multipart
import retrofit2.http.Url

class MainViewModel : ViewModel() {
    private val _translation = MutableLiveData<String>()
    val translation: MutableLiveData<String> = _translation

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun translate(file: MultipartBody.Part, onSuccess: (String) -> Unit, onError: (String) -> Unit) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().translate(file)
        client.enqueue(object : Callback<TranslateResponse> {
            override fun onResponse(
                call: Call<TranslateResponse>,
                response: Response<TranslateResponse>
            ) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        onSuccess(responseBody.result.toString())
                    } else {
                        onError("Response body is null")
                    }
                    _isLoading.value = false
                } else {
                    onError("onResponse: ${response.message()}")
                    _isLoading.value = false
                }
            }

            override fun onFailure(call: Call<TranslateResponse>, t: Throwable) {
                onError("onFailure: ${t.message}")
                _isLoading.value = false
            }
        })
    }
}