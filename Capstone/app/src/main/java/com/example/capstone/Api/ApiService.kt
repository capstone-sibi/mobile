package com.example.capstone.Api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ApiService {

    @GET("dictionary")
    fun getDictionary(
    ) : Call<DictionaryApiResponse>

    @POST("dictionary")
    @Multipart
    fun postDictionary(
        @Part("path") path : String,
        @Part("meaning") meaning : String
    ) : Call<DictionaryApiResponse>
}