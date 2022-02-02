package com.example.chuckjokes.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.PopupMenu
import androidx.fragment.app.Fragment
import com.example.chuckjokes.fragments.browser.BrowserFragment
import com.example.chuckjokes.fragments.error.ErrorFragment
import com.example.chuckjokes.fragments.jokes.JokesFragment
import com.example.chuckjokes.R
import com.example.chuckjokes.databinding.ActivityMainBinding
import com.example.chuckjokes.util.viewModelsExt

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
            viewModel.onNavigationItemItemSelected(item.itemId)
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


        // TODO: зачем вообще эта обработка
        viewModel.selectedNavItemId.observe(this) {
            //setSelectedNavItem(it)
        }

        viewModel.navigationEvent.observe(this) {
            changeFragment(
                when (it) {
                    R.id.jokes_item -> JokesFragment()
                    else -> BrowserFragment()
                }
            )
        }
    }

    private fun changeFragment(newFragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(binding.fragmentView.id, newFragment)
            .commit()
    }

    private fun setSelectedNavItem(id: Int) {
        binding.bottomNavigation.selectedItemId = id
    }

    fun setErrorFragment(ex: Throwable) {
        changeFragment(ErrorFragment.newInstance(ex))
    }

}