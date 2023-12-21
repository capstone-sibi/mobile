package com.example.capstone.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.capstone.data.homeApi.ApiConfig
import com.example.capstone.data.response.HomeResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.Url

class MainViewModel : ViewModel() {
    private val _translation = MutableLiveData<String>()
    val translation: MutableLiveData<String> = _translation

    fun translate(fullUrl: String, uri: String, onSuccess: (String) -> Unit, onError: (String) -> Unit) {
        val client = ApiConfig.getApiService().translate(fullUrl, uri)
        client.enqueue(object : Callback<HomeResponse> {
            override fun onResponse(
                call: Call<HomeResponse>,
                response: Response<HomeResponse>
            ) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        onSuccess(responseBody.result.toString())
                    } else {
                        onError("Response body is null")
                    }
                } else {
                    onError("onResponse: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<HomeResponse>, t: Throwable) {
                onError("onFailure: ${t.message}")
            }
        })
    }
}