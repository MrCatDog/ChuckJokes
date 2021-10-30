package com.example.chuckjokes.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.chuckjokes.R

class MainViewModel: ViewModel() {

    private val _selectedNavItemId = MutableLiveData<Int>()

    val selectedNavItemId : LiveData<Int>
        get() = _selectedNavItemId

    init {
        jokesItemSelected()
    }

    fun onNavigationItemItemSelected(itemId : Int): Boolean {
        when(itemId) {
            R.id.jokes_item -> jokesItemSelected()
            R.id.browser_item -> browserItemSelected()
            else -> return false
        }
        return true
    }

    private fun jokesItemSelected() {
        if (_selectedNavItemId.value != R.id.jokes_item) {
            _selectedNavItemId.value = R.id.jokes_item
        }
    }

    private fun browserItemSelected() {
        if (_selectedNavItemId.value != R.id.browser_item) {
            _selectedNavItemId.value = R.id.browser_item
        }
    }

    fun onAboutClicked() {
        // TODO: future task, show dialog about program and developer info for hire.
    }
}