package com.example.chuckjokes;

import com.example.chuckjokes.Browser.BrowserFragment;
import com.example.chuckjokes.Jokes.JokesFragment;

public class MainPresenter {

    private final MainActivity wireframe;

    public MainPresenter(MainActivity wireframe) {
        this.wireframe = wireframe;
    }

    public void jokesItemSelected() {
        wireframe.changeFragment(new JokesFragment(wireframe));
    }

    public void browserItemSelected() {
        wireframe.changeFragment(new BrowserFragment());
    }

    public void onAboutClicked() {
        // TODO
    }
}
