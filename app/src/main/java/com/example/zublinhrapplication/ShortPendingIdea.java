package com.example.zublinhrapplication;

public class ShortPendingIdea {
    private String author;
    private int id;
    private String ideaTitle;
    private String shortDescription;

    public ShortPendingIdea(){}

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

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdeaTitle(String ideaTitle) {
        this.ideaTitle = ideaTitle;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }
}
