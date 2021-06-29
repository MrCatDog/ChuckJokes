package com.example.chuckjokes.MainPresenter;

import android.view.View;

import androidx.appcompat.widget.PopupMenu;

import com.example.chuckjokes.MainActivity;
import com.example.chuckjokes.R;

public class MainPresenter {

    private final MainActivity wireframe;

    MainPresenter(MainActivity wireframe) {
        this.wireframe = wireframe;
    }

    public void init() {
        wireframe.setBottomNavigationItemListener(new BottomNavigationListener(wireframe));
        wireframe.setBottomSelectedItem(R.id.page_1); //а тут id не убрать?
    }

    public void menuBtnClicked(View v) {
        PopupMenu popup = new PopupMenu(wireframe, v);
        popup.setOnMenuItemClickListener(new ToolbarMenu());
        popup.inflate(R.menu.overflow_menu);
        popup.show();
    }
}
