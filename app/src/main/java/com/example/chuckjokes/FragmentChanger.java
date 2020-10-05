package com.example.chuckjokes;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class FragmentChanger {

    private final FragmentManager fragmentManager;

    FragmentChanger(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    public Runnable changeFragment(Fragment newFragment) {
        return () -> this.fragmentManager.beginTransaction().replace(R.id.fragmentView,newFragment).commit();
    }
}
