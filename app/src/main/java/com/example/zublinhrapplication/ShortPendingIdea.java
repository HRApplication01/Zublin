package com.example.zublinhrapplication;

public class ShortPendingIdea {
    private String author;
    private int id;
    private String ideaTitle;
    private String shortDescription;


    public ShortPendingIdea(String ideaTitle, int id, String shortDescription, String author) {
        this.author = author;
        this.id = id;
        this.ideaTitle = ideaTitle;
        this.shortDescription = shortDescription;
    }

    public String getAuthor() {
        return author;
    }

    public int getId() {
        return id;
    }

    public String getIdeaTitle() {
        return ideaTitle;
    }

    public String getShortDescription() {
        return shortDescription;
    }
}
