package com.example.chuckjokes;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    private final FragmentChanger fragmentChanger = new FragmentChanger(getSupportFragmentManager());
    private final ExecutorService executor = Executors.newSingleThreadExecutor();//Может нужно больше?

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationListener(this));
        bottomNavigationView.setSelectedItemId(R.id.page_1);

        setSupportActionBar(findViewById(R.id.toolbar));

        //getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);//?? NULL?
        //getSupportActionBar().setCustomView(R.layout.action_bar);
    }

    public void changeFragment(Fragment newFragment) {
        executor.submit(fragmentChanger.changeFragment(newFragment));
    }
}
