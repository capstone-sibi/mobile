package com.example.capstone.data

import com.example.capstone.data.homeApi.ApiService
import com.example.capstone.data.response.HomeResponse

class HomeRepository private constructor(
    private val apiService: ApiService
){

    suspend fun translate(uri: String) : HomeResponse {
        return apiService.translate(uri)
    }

}