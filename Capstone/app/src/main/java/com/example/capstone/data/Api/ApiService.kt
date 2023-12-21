package com.example.capstone.Api

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("dictionary")
    fun getDictionary(
    ) : Call<DictionaryApiResponse>
}