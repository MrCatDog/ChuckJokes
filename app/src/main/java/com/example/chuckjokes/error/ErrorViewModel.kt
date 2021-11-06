package com.example.chuckjokes.error

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.chuckjokes.Shared
import java.io.PrintWriter
import java.io.StringWriter
import java.lang.Exception

class ErrorViewModel : ViewModel() {

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
    }

    fun moreAboutErrorClicked() {
        _additionalInfoVisibility.value = _additionalInfoVisibility.value == false
    }

    fun setArguments(args: Bundle) {
        val exception = args.getSerializable(Shared.ERROR_EXCEPTION_TAG) as Exception

        val sw = StringWriter()
        exception.printStackTrace(PrintWriter(sw))
        _errorBaseInfoText.value = exception.localizedMessage
        _moreAboutErrorText.value = sw.toString()
    }
}