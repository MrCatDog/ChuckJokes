package com.example.chuckjokes.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.PopupMenu
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
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

        binding.bottomNavigation.setupWithNavController(binding.mainContainerView.getFragment<NavHostFragment>().navController)

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

        //TODO: разобраться с навигационным стеком.
    // По переходу на фрагмент ошибки, вернутся назад к ленте нельзя,
    // нижнее меню подсвечивает не тот элемент и выйти на главный экран нельзя вовсе.

        // TODO: теряется подсветка выбранного элемента. почему так? мб из-за того что выше.
//        viewModel.selectedNavItemId.observe(this) {
//            binding.bottomNavigation.selectedItemId = it
//        }
    }
}