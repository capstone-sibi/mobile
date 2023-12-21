package com.example.capstone.Api

data class DictionaryApiResponse(
    var data: List<DictionaryResponse>? = null,
    var status: String? = null,
    var message: String? = null
)
