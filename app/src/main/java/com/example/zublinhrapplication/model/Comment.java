package com.example.zublinhrapplication.model;

public class Comment {
    private Long id;
    private Long ideaId;
    private String author;
    private String comments;

    public Comment() {}

    public Comment(Long id, Long ideaId, String author, String comments) {
        this.id = id;
        this.ideaId = ideaId;
        this.author = author;
        this.comments = comments;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdeaId() {
        return ideaId;
    }

    public void setIdeaId(Long ideaId) {
        this.ideaId = ideaId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
