package com.example.chuckjokes.fragments.browser

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.chuckjokes.util.MutableLiveEvent

const val MAX_PROGRESS = 100
const val DEFAULT_URL = "http://www.icndb.com/api/"

class BrowserViewModel : ViewModel() {

    private val _progress = MutableLiveData<Int>()
    val progress : LiveData<Int>
        get() = _progress

    private val _visibility = MutableLiveData<Boolean>()
    val visibility : LiveData<Boolean>
        get() = _visibility

    private val _url = MutableLiveData(DEFAULT_URL)
    val url : LiveData<String>
        get() = _url

    fun changeProgress(newProgress: Int) {
        _progress.value = newProgress
        _visibility.value = newProgress != MAX_PROGRESS
    }

    fun shouldOverrideUrlLoading() : Boolean {
        return false
    }
}