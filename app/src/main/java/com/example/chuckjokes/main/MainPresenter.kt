package com.example.chuckjokes.main

import com.example.chuckjokes.R

class MainPresenter(private val wireframe: MainActivity) {

    private var selectedNavItemId = 0

    enum class Direction {
        JOKES,
        BROWSER,
        ERROR;
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
        if (selectedNavItemId != R.id.jokes_item) {
            selectedNavItemId = R.id.jokes_item
            wireframe.setJokesFragment()
            wireframe.setSelectedNavItem(R.id.jokes_item)
        }
    }

    private fun browserItemSelected() {
        if (selectedNavItemId != R.id.browser_item) {
            selectedNavItemId = R.id.browser_item
            wireframe.setBrowserFragment()
            wireframe.setSelectedNavItem(R.id.browser_item)
        }
    }



    fun onAboutClicked() {
        // TODO
    }

    init {
        jokesItemSelected()
    }
}