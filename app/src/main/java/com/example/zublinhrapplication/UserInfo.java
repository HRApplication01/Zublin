package com.example.zublinhrapplication;

import android.content.Intent;

public class UserInfo {
    private String username;
    private String name;

    public UserInfo(String username, String name) {
        this.username = username;
        this.name = name;
    }

    public UserInfo(Intent i) {
        this.username = i.getStringExtra("username");
        this.name = i.getStringExtra("name");
    }

    public void setString(Intent i) {
        i.putExtra("username", username);
        i.putExtra("name", name);
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }
}
