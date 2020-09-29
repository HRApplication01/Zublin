package com.example.zublinhrapplication;

public class ShortPendingIdea {
    private String author;
    private String ideaTitle;
    private String shortDescription;


    public ShortPendingIdea(String ideaTitle, String shortDescription, String author) {
        this.author = author;
        this.ideaTitle = ideaTitle;
        this.shortDescription = shortDescription;
    }

    public String getAuthor() {
        return author;
    }

    public String getIdeaTitle() {
        return ideaTitle;
    }

    public String getShortDescription() {
        return shortDescription;
    }
}
