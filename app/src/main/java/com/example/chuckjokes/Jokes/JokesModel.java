package com.example.chuckjokes.Jokes;

import java.util.ArrayList;

public class JokesModel {

    private ArrayList<JokeItem> items = new ArrayList<>();

    public int getArrayLength() {
        return items.size();
    }

    public void add(JokeItem object) {
        items.add(object);
    }

    public JokeItem getItem(int pos) {
        return items.get(pos);
    }
}
