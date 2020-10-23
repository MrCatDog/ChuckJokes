package com.example.chuckjokes;

import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.chuckjokes.Browser.BrowserFragment;
import com.example.chuckjokes.Jokes.JokesFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class BottomNavigationListener implements BottomNavigationView.OnNavigationItemSelectedListener {

    private Fragment fragment;
    private final MainActivity mainActivity;

    BottomNavigationListener(@NonNull MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.page_1:
                fragment = new JokesFragment(mainActivity);
                break;
            case R.id.page_2:
                fragment = new BrowserFragment();
                break;
        }
        mainActivity.changeFragment(fragment);
        return true;
    }
}
