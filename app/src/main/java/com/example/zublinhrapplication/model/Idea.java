package com.example.zublinhrapplication.model;

public class Idea {

    private boolean adv1Checked;
    private boolean adv2Checked;
    private boolean adv3Checked;
    private boolean adv4Checked;
    private String author;
    private String description;
    private String feasibility;
    private Long id;
    private String ideaTitle;
    private String locationSite;
    private boolean mySelf;
    private boolean other;
    private Long pending;
    private boolean premium1Checked;
    private boolean premium2Checked;
    private boolean premium3Checked;
    private String problem;
    private String solution;
    private String solutionTriedDesc;
    private String title;
    private boolean tried1Checked;
    private boolean tried2Checked;

    public Idea() {
    }

    public Idea(boolean adv1Checked, boolean adv2Checked, boolean adv3Checked, boolean adv4Checked, String author, String description, String feasibility, Long id, String ideaTitle, String locationSite, boolean mySelf, boolean other, Long pending, boolean premium1Checked, boolean premium2Checked, boolean premium3Checked, String problem, String solution, String solutionTriedDesc, String title, boolean tried1Checked, boolean tried2Checked) {
        this.adv1Checked = adv1Checked;
        this.adv2Checked = adv2Checked;
        this.adv3Checked = adv3Checked;
        this.adv4Checked = adv4Checked;
        this.author = author;
        this.description = description;
        this.feasibility = feasibility;
        this.id = id;
        this.ideaTitle = ideaTitle;
        this.locationSite = locationSite;
        this.mySelf = mySelf;
        this.other = other;
        this.pending = pending;
        this.premium1Checked = premium1Checked;
        this.premium2Checked = premium2Checked;
        this.premium3Checked = premium3Checked;
        this.problem = problem;
        this.solution = solution;
        this.solutionTriedDesc = solutionTriedDesc;
        this.title = title;
        this.tried1Checked = tried1Checked;
        this.tried2Checked = tried2Checked;
    }

    public boolean isAdv1Checked() {
        return adv1Checked;
    }

    public void setAdv1Checked(boolean adv1Checked) {
        this.adv1Checked = adv1Checked;
    }

    public boolean isAdv2Checked() {
        return adv2Checked;
    }

    public void setAdv2Checked(boolean adv2Checked) {
        this.adv2Checked = adv2Checked;
    }

    public boolean isAdv3Checked() {
        return adv3Checked;
    }

    public void setAdv3Checked(boolean adv3Checked) {
        this.adv3Checked = adv3Checked;
    }

    public boolean isAdv4Checked() {
        return adv4Checked;
    }

    public void setAdv4Checked(boolean adv4Checked) {
        this.adv4Checked = adv4Checked;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFeasibility() {
        return feasibility;
    }

    public void setFeasibility(String feasibility) {
        this.feasibility = feasibility;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdeaTitle() {
        return ideaTitle;
    }

    public void setIdeaTitle(String ideaTitle) {
        this.ideaTitle = ideaTitle;
    }

    public String getLocationSite() {
        return locationSite;
    }

    public void setLocationSite(String locationSite) {
        this.locationSite = locationSite;
    }

    public boolean isMySelf() {
        return mySelf;
    }

    public void setMySelf(boolean mySelf) {
        this.mySelf = mySelf;
    }

    public boolean isOther() {
        return other;
    }

    public void setOther(boolean other) {
        this.other = other;
    }

    public Long getPending() {
        return pending;
    }

    public void setPending(Long pending) {
        this.pending = pending;
    }

    public boolean isPremium1Checked() {
        return premium1Checked;
    }

    public void setPremium1Checked(boolean premium1Checked) {
        this.premium1Checked = premium1Checked;
    }

    public boolean isPremium2Checked() {
        return premium2Checked;
    }

    public void setPremium2Checked(boolean premium2Checked) {
        this.premium2Checked = premium2Checked;
    }

    public boolean isPremium3Checked() {
        return premium3Checked;
    }

    public void setPremium3Checked(boolean premium3Checked) {
        this.premium3Checked = premium3Checked;
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public String getSolutionTriedDesc() {
        return solutionTriedDesc;
    }

    public void setSolutionTriedDesc(String solutionTriedDesc) {
        this.solutionTriedDesc = solutionTriedDesc;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isTried1Checked() {
        return tried1Checked;
    }

    public void setTried1Checked(boolean tried1Checked) {
        this.tried1Checked = tried1Checked;
    }

    public boolean isTried2Checked() {
        return tried2Checked;
    }

    public void setTried2Checked(boolean tried2Checked) {
        this.tried2Checked = tried2Checked;
    }
}
