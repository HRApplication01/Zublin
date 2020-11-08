package com.example.zublinhrapplication.model;

public class User {
    private Long accountType;
    private Boolean approvedUser;
    private String name;
    private String password;
    private String username;

    public User(Long accountType, Boolean approvedUser, String name, String password, String username) {
        this.accountType = accountType;
        this.approvedUser = approvedUser;
        this.name = name;
        this.password = password;
        this.username = username;
    }

    public User() {
    }

    public Long getAccountType() {
        return accountType;
    }

    public void setAccountType(Long accountType) {
        this.accountType = accountType;
    }

    public Boolean getApprovedUser() {
        return approvedUser;
    }

    public void setApprovedUser(Boolean approvedUser) {
        this.approvedUser = approvedUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
