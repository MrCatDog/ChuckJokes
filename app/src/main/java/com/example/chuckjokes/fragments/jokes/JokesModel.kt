package com.example.chuckjokes.fragments.jokes

import java.util.ArrayList

class JokesModel {

    class JokeItem(val id: Int, val text: String, val categories: String)

    val items = ArrayList<JokeItem>()
    var isLoading = false

}