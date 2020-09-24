package com.example.chuckjokes;

import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.lang.ref.WeakReference;


public class BottomNavigationListener implements BottomNavigationView.OnNavigationItemSelectedListener {

    private JokesFragment jokesFragment;
    private BrowserFragment browserFragment;
    private WeakReference<MainActivity> mainActivityWeakReference;

    BottomNavigationListener(@NonNull MainActivity mainActivity) {
        mainActivityWeakReference = new WeakReference<>(mainActivity);
        //jokesFragment = new JokesFragment(manager);
        browserFragment = new BrowserFragment();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.page_1:

                //manager.beginTransaction().replace(R.id.fragmentView,jokesFragment).commit();
                break;
            case R.id.page_2:
                //manager.beginTransaction().replace(R.id.fragmentView,browserFragment).commit();
                break;
        }
        return true;
    }
}
