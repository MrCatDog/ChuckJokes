package com.example.chuckjokes.util

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ServerApi {
    @GET("jokes/random/{number}?escape=javascript")
    fun getRandomJokes(@Path("number") jokesNumber: Int) : Call<JokesResponse>
}