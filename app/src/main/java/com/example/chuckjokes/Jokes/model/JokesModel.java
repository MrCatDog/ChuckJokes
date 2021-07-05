package com.example.chuckjokes.Jokes.model;

import java.util.ArrayList;

public class JokesModel {

    private ArrayList<JokeItem> items = new ArrayList<>();

    public void add(JokeItem object) {
        items.add(object);
    }

    public ArrayList<JokeItem> getArray() {
        return items;
    }
}
