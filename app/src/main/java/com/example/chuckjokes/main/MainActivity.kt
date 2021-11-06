package com.example.chuckjokes.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.PopupMenu
import androidx.fragment.app.Fragment
import com.example.chuckjokes.browser.BrowserFragment
import com.example.chuckjokes.error.ErrorFragment
import com.example.chuckjokes.jokes.JokesFragment
import com.example.chuckjokes.R
import com.example.chuckjokes.Shared
import com.example.chuckjokes.databinding.ActivityMainBinding
import com.example.chuckjokes.viewModelsExt
import com.example.chuckjokes.Shared.Direction.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel by viewModelsExt {
        MainViewModel()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavigation.setOnItemSelectedListener { item: MenuItem ->
            viewModel.onNavigationItemItemSelected(
                item.itemId
            )
        }

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
            changeFragmentByDirection(direction = it)
        }
    }

    private fun changeFragment(newFragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(binding.fragmentView.id, newFragment)
            .commit()
    }

    fun changeFragmentByDirection(direction: Shared.Direction, args: Bundle? = null) {
        changeFragment(
            when (direction) {
                JOKES -> {
                    setSelectedNavItem(R.id.jokes_item)
                    JokesFragment()
                }
                BROWSER -> {
                    setSelectedNavItem(R.id.browser_item)
                    BrowserFragment()
                }
                ERROR -> {
                    ErrorFragment.newInstance(args)
                }
            }
        )
    }

    private fun setSelectedNavItem(id: Int) {
        binding.bottomNavigation.selectedItemId = id
    }
}