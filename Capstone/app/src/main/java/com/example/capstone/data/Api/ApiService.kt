package com.example.capstone.data.Api

import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Multipart
import retrofit2.http.POST

interface ApiService {
    @Multipart
    @FormUrlEncoded

    @POST("dictionary")
    suspend fun DictionaryResponse(
        @Field("path") path: String,
        @Field("meaning") meaning: String
    ) : DictionaryResponse
}