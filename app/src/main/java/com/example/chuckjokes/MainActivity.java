package com.example.chuckjokes;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ContextThemeWrapper;
import androidx.appcompat.widget.PopupMenu;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.widget.ImageButton;

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

        ImageButton menu = findViewById(R.id.menuBtn);
        menu.setOnClickListener(v -> {
            //ContextThemeWrapper ctw = new ContextThemeWrapper(MainActivity.this, R.style.CustomPopup);
            //PopupMenu popup = new PopupMenu(ctw, v);
            PopupMenu popup = new PopupMenu(MainActivity.this, v);
            popup.setOnMenuItemClickListener(new ToolbarMenu());
            popup.inflate(R.menu.overflow_menu);
            popup.show();
        });

    }

    public void changeFragment(Fragment newFragment) {
        executor.submit(fragmentChanger.changeFragment(newFragment));
    }

}
