package com.example.capstone.data.homeApi

import com.example.capstone.data.response.HomeResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import retrofit2.http.Url

interface ApiService {
    @FormUrlEncoded
    @POST()
    fun translate(
        @Url fullUrl: String,
        @Field("url") uri: String
    ): Call<HomeResponse>
}