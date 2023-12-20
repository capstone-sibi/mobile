package com.example.capstone.Api

import com.google.gson.annotations.SerializedName

data class DictionaryResponse(

	@field:SerializedName("path")
	val path: String? = null,

	@field:SerializedName("meaning")
	val meaning: String? = null
)
