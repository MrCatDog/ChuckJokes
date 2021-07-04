package com.example.chuckjokes.Jokes;

public class JokeItem {

    private final int id;
    private final String text;
    private final String categories;

    public JokeItem(int id, String text, String categories) {
        this.id = id;
        this.text = text;
        this.categories = categories;
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public String getCategories() {
        return categories;
    }
}
