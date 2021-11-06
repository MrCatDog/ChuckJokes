package com.example.chuckjokes.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.chuckjokes.R
import com.example.chuckjokes.Shared
import com.example.chuckjokes.Shared.Direction.*


class MainViewModel: ViewModel() {

    private val _selectedNavItemId = MutableLiveData<Shared.Direction>()

    val selectedNavItemId : LiveData<Shared.Direction>
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
        if (_selectedNavItemId.value != JOKES) {
            _selectedNavItemId.value = JOKES
        }
    }

    private fun browserItemSelected() {
        if (_selectedNavItemId.value != BROWSER) {
            _selectedNavItemId.value = BROWSER
        }
    }

    fun onAboutClicked() {
        // TODO: future task, show dialog about program and developer info for hire.
    }
}