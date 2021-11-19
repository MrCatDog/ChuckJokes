package com.example.chuckjokes.fragments.error

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.chuckjokes.util.MutableLiveEvent
import java.io.PrintWriter
import java.io.StringWriter
import kotlin.Exception

class ErrorViewModel(ex : Exception) : ViewModel() {

    private val _additionalInfoVisibility = MutableLiveData<Boolean>()
    val additionalInfoVisibility : LiveData<Boolean>
        get() = _additionalInfoVisibility

    private val _errorBaseInfoText = MutableLiveData<String>()
    val errorBaseInfoText : LiveData<String>
        get() = _errorBaseInfoText

    private val _moreAboutErrorText = MutableLiveData<String>()
    val moreAboutErrorText : LiveData<String>
        get() = _moreAboutErrorText

    init {
        _additionalInfoVisibility.value = false

        val sw = StringWriter()
        ex.printStackTrace(PrintWriter(sw))
        _errorBaseInfoText.value = ex.localizedMessage
        _moreAboutErrorText.value = sw.toString()
    }

    fun moreAboutErrorClicked() {
        _additionalInfoVisibility.value = _additionalInfoVisibility.value == false
    }
}