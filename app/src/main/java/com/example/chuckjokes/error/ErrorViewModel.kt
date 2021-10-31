package com.example.chuckjokes.error

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ErrorViewModel : ViewModel() {

    private val _additionalInfoVisibility = MutableLiveData<Int>()

    val selectedNavItemId : LiveData<Int>
        get() = _additionalInfoVisibility

    init {
        _additionalInfoVisibility.value = View.GONE
    }

    fun moreAboutErrorClicked() {
        if (_additionalInfoVisibility.value == View.GONE) {
            _additionalInfoVisibility.value = View.VISIBLE
        } else {
            _additionalInfoVisibility.value = View.GONE
        }
    }
}