package com.example.chuckjokes.fragments.jokes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.chuckjokes.util.JokesResponse
import com.example.chuckjokes.util.ServerApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "http://api.icndb.com/"
const val JOKES_VALUE = 15
const val VISIBLE_THRESHOLD = 5

const val JOKE_CATEGORY_DELIMITER = ", "

class JokesViewModel : ViewModel() {

    private val model: JokesModel = JokesModel()

    private val serverApi: ServerApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ServerApi::class.java)

    private val _jokes = MutableLiveData<List<JokesModel.JokeItem>>()
    val jokes: LiveData<List<JokesModel.JokeItem>>
        get() = _jokes

    private val _exception = MutableLiveData<Throwable>()
    val exception: LiveData<Throwable>
        get() = _exception

    init {
        downloadMore()
    }

    private fun downloadMore() {
        if (model.isLoading) {
            return
        }
        model.isLoading = true

        serverApi.getRandomJokes(JOKES_VALUE)
            .enqueue(object : Callback<JokesResponse> {
                override fun onFailure(call: Call<JokesResponse>, t: Throwable) {
                    _exception.value = t
                }

                override fun onResponse(
                    call: Call<JokesResponse>,
                    response: Response<JokesResponse>
                ) {
                    response.body()?.value?.map {
                        JokesModel.JokeItem(
                            id = it.id,
                            text = it.text,
                            categories = it.categories.joinToString(separator = JOKE_CATEGORY_DELIMITER)
                        )
                    }.let {
                        if (it != null) { //todo пиздец какой, проверка на null внутри let
                            model.items.addAll(it)
                        }
                    } //todo придумать что-то с обработкой этой ошибки и не только, у тебя ещё и Value нулабельный
                    _jokes.value = model.items
                    model.isLoading = false
                }
            })
    }

    fun onScrolledToEnd(lastVisibleItemPosition: Int, itemCount: Int) {
        if (lastVisibleItemPosition + VISIBLE_THRESHOLD > itemCount) {
            downloadMore()
        }
    }
}