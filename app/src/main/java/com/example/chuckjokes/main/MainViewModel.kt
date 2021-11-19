package com.example.chuckjokes.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.chuckjokes.util.MutableLiveEvent
import com.example.chuckjokes.R

class MainViewModel : ViewModel() {

    private val _selectedNavItemId = MutableLiveData<Int>()
    val selectedNavItemId: LiveData<Int>
        get() = _selectedNavItemId

    private val _navigationEvent = MutableLiveEvent<Int>()
    val navigationEvent: LiveData<Int>
        get() = _navigationEvent

    init {
        setFragment(R.id.jokes_item)
    }

    fun onNavigationItemItemSelected(itemId: Int): Boolean =
        if (_selectedNavItemId.value != itemId) {
            setFragment(itemId)
            true
        } else {
            false
        }

    private fun setFragment(id: Int) {
        _selectedNavItemId.value = id
        _navigationEvent.setValue(id)
    }

    fun onAboutClicked() {
        // TODO: future task, show dialog about program and developer info for hire.
    }
}