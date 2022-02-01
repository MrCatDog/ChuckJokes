package com.example.chuckjokes.util

import com.google.gson.annotations.SerializedName

data class JokeItemResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("joke")
    val text: String,
    @SerializedName("categories")
    val categories: List<String>
)