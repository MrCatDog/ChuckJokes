package com.example.chuckjokes.Jokes.model;

import java.util.ArrayList;

public class JokesModel {

    private ArrayList<JokeItem> items = new ArrayList<>();
    private boolean isLoading = false;

    public void add(JokeItem object) {
        items.add(object);
    }

    public ArrayList<JokeItem> getItems() {
        return items;
    }

    public boolean isLoading() {
        return isLoading;
    }

    public void setLoading(boolean loading) {
        isLoading = loading;
    }
}
