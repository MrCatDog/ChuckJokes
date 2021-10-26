package com.example.chuckjokes.jokes

import java.util.ArrayList

class JokesModel {
    val items = ArrayList<JokeItem>()
    var isLoading = false
    fun add(`object`: JokeItem) {
        items.add(`object`)
    }
}