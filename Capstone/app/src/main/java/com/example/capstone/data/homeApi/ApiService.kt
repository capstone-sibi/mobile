package com.example.capstone.data.homeApi

import com.example.capstone.data.response.HomeResponse
import com.example.capstone.data.response.TranslateResponse
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Url

interface ApiService {
    @FormUrlEncoded
    @POST()
    fun home(
        @Url fullUrl: String,
        @Field("url") uri: String
    ): Call<HomeResponse>

    @Multipart
    @POST("predict")
    fun translate(
        @Part file: MultipartBody.Part
    ): Call<TranslateResponse>
}