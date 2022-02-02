package com.example.chuckjokes.util

import com.google.gson.annotations.SerializedName

data class JokesResponse(
    @SerializedName("success")
    val success: String,
    @SerializedName("value")
    val value: List<JokeItemResponse>
)

data class JokeItemResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("joke")
    val text: String,
    @SerializedName("categories")
    val categories: List<String>
)
