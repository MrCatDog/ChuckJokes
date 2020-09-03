package com.example.chuckjokes;

import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;


public class BottomNavigationListener implements BottomNavigationView.OnNavigationItemSelectedListener {

    private FragmentManager manager;
    private JokesFragment jokesFragment;
    private BrowserFragment browserFragment;

    BottomNavigationListener(@NonNull FragmentManager fragmentManager) {
        manager = fragmentManager;
        jokesFragment = new JokesFragment();
        browserFragment = new BrowserFragment();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.page_1:
                manager.beginTransaction().replace(R.id.fragmentView,jokesFragment).commit();
                break;
            case R.id.page_2:
                manager.beginTransaction().replace(R.id.fragmentView,browserFragment).commit();
                break;
        }
        return true;
    }
}
