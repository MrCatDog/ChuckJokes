package com.example.chuckjokes.jokes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.util.concurrent.Callable
import java.util.concurrent.Executors
import java.util.concurrent.FutureTask

object JokesConstants {
    const val JOKES_VALUE = 15
    const val BASE_URL = "http://api.icndb.com/jokes/random/"
    const val ESCAPE_JS = "?escape=javascript" //for correct form of quotes(non &quot;)

    const val ANSWER_TAG = "value"
    const val JOKE_ID_TAG = "id"
    const val JOKE_CATEGORY_TAG = "categories"
    const val JOKE_TEXT_TAG = "joke"
}

class JokesViewModel : ViewModel() {

    private val model: JokesModel = JokesModel()
    private val executor = Executors.newSingleThreadExecutor()
    private val client = OkHttpClient()

    private val _jokes = MutableLiveData<List<JokesModel.JokeItem>>()
    val jokes: LiveData<List<JokesModel.JokeItem>>
        get() = _jokes

    private val _exception = MutableLiveData<Exception>()
    val exception: LiveData<Exception>
        get() = _exception

    fun onScrolledToEnd() {
        if (model.isLoading) {
            return
        }
        model.isLoading = true

        val future = FutureTask(Callable<String> {
            val request: Request = Request.Builder().url(
                JokesConstants.BASE_URL +
                        JokesConstants.JOKES_VALUE +
                        JokesConstants.ESCAPE_JS
            ).build()

            client.newCall(request).execute().use { response ->
                if (response.isSuccessful) {
                    return@Callable response.body?.string()
                }
            }
            null
        })

        try {
            executor.submit(future)
            val answer = JSONObject(future.get()).getJSONArray(JokesConstants.ANSWER_TAG)
            var `object`: JSONObject
            for (i in 0 until JokesConstants.JOKES_VALUE) {
                `object` = answer.getJSONObject(i)
                model.items.add(
                    JokesModel.JokeItem(
                        `object`.getInt(JokesConstants.JOKE_ID_TAG),
                        `object`.getString(JokesConstants.JOKE_TEXT_TAG),
                        jsonToString(`object`.getJSONArray(JokesConstants.JOKE_CATEGORY_TAG))
                    )
                )
            }
            _jokes.value = model.items
        } catch (ex: Exception) {
            _exception.value = ex
        }
        model.isLoading = false
    }

    private fun jsonToString(ja: JSONArray) = try {
        var str = ja.getString(0)
        // TODO: ja.join(",") - вот если бы эта фигня возвращала ответ без идиотских кавычек
        for (i in 1 until ja.length()) {
            str = str + "," + ja.getString(i)
        }
        str
    } catch (ex: Exception) {
        ""
    }
}