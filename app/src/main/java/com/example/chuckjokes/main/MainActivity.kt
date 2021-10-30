package com.example.chuckjokes.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.PopupMenu
import androidx.fragment.app.Fragment
import com.example.chuckjokes.BrowserFragment
import com.example.chuckjokes.ErrorFragment
import com.example.chuckjokes.jokes.JokesFragment
import com.example.chuckjokes.R
import com.example.chuckjokes.databinding.ActivityMainBinding
import com.example.chuckjokes.viewModelsExt
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel by viewModelsExt {
        MainViewModel()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavigation.setOnItemSelectedListener { item: MenuItem -> viewModel.onNavigationItemItemSelected(item.itemId) }

        binding.toolbar.menuBtn.setOnClickListener { v: View ->
            val popup = PopupMenu(this, v)
            popup.setOnMenuItemClickListener { item: MenuItem ->
                when (item.itemId) {
                    R.id.about -> {
                        viewModel.onAboutClicked()
                        return@setOnMenuItemClickListener true
                    }
                    else -> return@setOnMenuItemClickListener false
                }
            }
            popup.inflate(R.menu.overflow_menu)
            popup.show()
        }

        viewModel.selectedNavItemId.observe(this) {
            when(it) {
                R.id.jokes_item -> setJokesFragment()
                R.id.browser_item -> setBrowserFragment()
            }
        }
    }

    private fun changeFragment(newFragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(binding.fragmentView.id, newFragment).commit()
    }

    fun setJokesFragment() {
        changeFragment(JokesFragment())
        setSelectedNavItem(R.id.jokes_item)
    }

    fun setBrowserFragment() {
        changeFragment(BrowserFragment())
        setSelectedNavItem(R.id.browser_item)
    }

    fun setErrorFragment(exception: Exception?) {
        changeFragment(ErrorFragment(exception))
    }

    private fun setSelectedNavItem(id: Int) {
        binding.bottomNavigation.selectedItemId = id
    }
}