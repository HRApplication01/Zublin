package com.example.zublinhrapplication.model;

public class Idea {
    private boolean pending;
    private String title;
    private String author;
    private String submitter;
    private Long id;
    private String problemDescription;
    private String solutionDescription;
    private Feasibility feasibility;
    private boolean advRed;
    private boolean advQual;
    private boolean advSafe;
    private boolean advPub;
    private boolean tried;
    private String implementation;
    private boolean premPowerBank;
    private boolean premUmbrella;
    private boolean premRunningShirt;

    public Idea() {
    }

    public Idea(boolean pending, String title, String author, String submitter, Long id, String problemDescription, String solutionDescription, Feasibility feasibility, boolean advRed, boolean advQual, boolean advSafe, boolean advPub, boolean tried, String implementation, boolean premPowerBank, boolean premUmbrella, boolean premRunningShirt) {
        this.pending = pending;
        this.title = title;
        this.author = author;
        this.submitter = submitter;
        this.id = id;
        this.problemDescription = problemDescription;
        this.solutionDescription = solutionDescription;
        this.feasibility = feasibility;
        this.advRed = advRed;
        this.advQual = advQual;
        this.advSafe = advSafe;
        this.advPub = advPub;
        this.tried = tried;
        this.implementation = implementation;
        this.premPowerBank = premPowerBank;
        this.premUmbrella = premUmbrella;
        this.premRunningShirt = premRunningShirt;
    }

    public boolean isPending() {
        return pending;
    }

    public void setPending(boolean pending) {
        this.pending = pending;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getSubmitter() {
        return submitter;
    }

    public void setSubmitter(String submitter) {
        this.submitter = submitter;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProblemDescription() {
        return problemDescription;
    }

    public void setProblemDescription(String problemDescription) {
        this.problemDescription = problemDescription;
    }

    public String getSolutionDescription() {
        return solutionDescription;
    }

    public void setSolutionDescription(String solutionDescription) {
        this.solutionDescription = solutionDescription;
    }

    public Feasibility getFeasibility() {
        return feasibility;
    }

    public void setFeasibility(Feasibility feasibility) {
        this.feasibility = feasibility;
    }

    public boolean isAdvRed() {
        return advRed;
    }

    public void setAdvRed(boolean advRed) {
        this.advRed = advRed;
    }

    public boolean isAdvQual() {
        return advQual;
    }

    public void setAdvQual(boolean advQual) {
        this.advQual = advQual;
    }

    public boolean isAdvSafe() {
        return advSafe;
    }

    public void setAdvSafe(boolean advSafe) {
        this.advSafe = advSafe;
    }

    public boolean isAdvPub() {
        return advPub;
    }

    public void setAdvPub(boolean advPub) {
        this.advPub = advPub;
    }

    public boolean isTried() {
        return tried;
    }

    public void setTried(boolean tried) {
        this.tried = tried;
    }

    public String getImplementation() {
        return implementation;
    }

    public void setImplementation(String implementation) {
        this.implementation = implementation;
    }

    public boolean isPremPowerBank() {
        return premPowerBank;
    }

    public void setPremPowerBank(boolean premPowerBank) {
        this.premPowerBank = premPowerBank;
    }

    public boolean isPremUmbrella() {
        return premUmbrella;
    }

    public void setPremUmbrella(boolean premUmbrella) {
        this.premUmbrella = premUmbrella;
    }

    public boolean isPremRunningShirt() {
        return premRunningShirt;
    }

    public void setPremRunningShirt(boolean premRunningShirt) {
        this.premRunningShirt = premRunningShirt;
    }
}
