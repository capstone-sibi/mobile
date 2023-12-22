package com.example.capstone.data.response

import com.google.gson.annotations.SerializedName

data class TranslateResponse(

	@field:SerializedName("result")
	val result: String? = null,

	@field:SerializedName("url_path")
	val urlPath: String? = null
)
