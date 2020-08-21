package com.example.chuckjokes;

import android.view.MenuItem;

import androidx.annotation.NonNull;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BottomNavigationListener implements BottomNavigationView.OnNavigationItemSelectedListener {

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.page_1:

                break;
            case R.id.page_2:

                break;
        }
        return true;
    }
}
