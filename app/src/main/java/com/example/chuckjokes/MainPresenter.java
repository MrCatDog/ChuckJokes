package com.example.chuckjokes;

public class MainPresenter {

    private final MainActivity wireframe;
    private int selectedNavItemId = 0;

    public MainPresenter(MainActivity wireframe) {
        this.wireframe = wireframe;
        jokesItemSelected();
    }

    public void jokesItemSelected() {
        if (selectedNavItemId != R.id.jokes_item) {
            selectedNavItemId = R.id.jokes_item;
            wireframe.setJokesFragment();
            wireframe.setSelectedNavItem(R.id.jokes_item);
        }
    }

    public void browserItemSelected() {
        if (selectedNavItemId != R.id.browser_item) {
            selectedNavItemId = R.id.browser_item;
            wireframe.setBrowserFragment();
            wireframe.setSelectedNavItem(R.id.browser_item);
        }
    }

    public void onAboutClicked() {
        // TODO
    }
}
