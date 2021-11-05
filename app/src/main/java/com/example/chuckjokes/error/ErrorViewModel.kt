package com.example.chuckjokes.error

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ErrorViewModel : ViewModel() {

    private val _additionalInfoVisibility = MutableLiveData<Boolean>()

    val additionalInfoVisibility : LiveData<Boolean>
        get() = _additionalInfoVisibility

    init {
        _additionalInfoVisibility.value = false
    }

    fun moreAboutErrorClicked() {
        _additionalInfoVisibility.value = _additionalInfoVisibility.value == false
    }
}