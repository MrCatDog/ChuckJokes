package com.example.chuckjokes;

public class MainPresenter {

    private final MainActivity wireframe;

    public MainPresenter(MainActivity wireframe) {
        this.wireframe = wireframe;
    }

    public void jokesItemSelected() {
        wireframe.setJokesFragment();
    }

    public void browserItemSelected() {
        wireframe.setBrowserFragment();
    }

    public void onAboutClicked() {
        // TODO
    }
}
