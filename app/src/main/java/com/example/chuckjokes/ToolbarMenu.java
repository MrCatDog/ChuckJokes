package com.example.chuckjokes;

import android.view.MenuItem;

import androidx.appcompat.widget.PopupMenu;

public class ToolbarMenu implements PopupMenu.OnMenuItemClickListener {
    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.about:

                return true;
            default:
                return false;
        }
    }
}
