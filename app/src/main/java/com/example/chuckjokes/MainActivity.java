package com.example.chuckjokes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.widget.ImageButton;

import com.example.chuckjokes.MainPresenter.MainPresenter;
import com.example.chuckjokes.databinding.ActivityMainBinding;
import com.example.chuckjokes.databinding.CustomToolbarBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private CustomToolbarBinding toolbarBinding;
    private MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        toolbarBinding = CustomToolbarBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        presenter = new MainPresenter(this);
        presenter.init();

        ImageButton menu = findViewById(R.id.menuBtn);
        menu.setOnClickListener(v-> presenter.menuBtnClicked(v));//с биндингом не работает, поЧАМУ?
    }

    public void setBottomNavigationItemListener(BottomNavigationView.OnNavigationItemSelectedListener listener) {
        binding.bottomNavigation.setOnNavigationItemSelectedListener(listener);
    }

    public void setBottomSelectedItem(int itemID) {
        binding.bottomNavigation.setSelectedItemId(itemID);
    }

    public void changeFragment(Fragment newFragment) {
        getSupportFragmentManager().beginTransaction().replace(binding.fragmentView.getId(), newFragment).commit();
    }

}
