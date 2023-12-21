package com.example.capstone.Api

import com.google.gson.annotations.SerializedName

data class DictionaryResponse(

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("path")
	val path: String? = null,

	@field:SerializedName("meaning")
	val meaning: String? = null,

	@field:SerializedName("createdAt")
	val createdAt: String? = null,

	@field:SerializedName("updatedAt")
	val updatedAt: String? = null
)
