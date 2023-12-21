package com.example.capstone.data.homeApi

import com.example.capstone.data.response.HomeResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {
    @FormUrlEncoded
    @POST
    suspend fun translate(
        @Field("url") uri: String
    ): HomeResponse
}