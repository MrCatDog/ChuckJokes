package com.example.chuckjokes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.example.chuckjokes.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        presenter = new MainPresenter(this);

        binding.bottomNavigation.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.jokes_item:
                    presenter.jokesItemSelected();
                    break;
                case R.id.browser_item:
                    presenter.browserItemSelected();
                    break;
            }
            return true;
        });
        binding.bottomNavigation.setSelectedItemId(R.id.jokes_item);

        binding.toolbar.menuBtn.setOnClickListener(v -> {
            PopupMenu popup = new PopupMenu(this, v);
            popup.setOnMenuItemClickListener(item -> {
                switch (item.getItemId()) {
                    case R.id.about:
                        presenter.onAboutClicked();
                        return true;
                    default:
                        return false;
                }
            });
            popup.inflate(R.menu.overflow_menu);
            popup.show();
        });
    }

    public void changeFragment(Fragment newFragment) {
        getSupportFragmentManager().beginTransaction().replace(binding.fragmentView.getId(), newFragment).commit();
    }

}
