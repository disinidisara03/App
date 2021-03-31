package com.firstapp.ceylonhearts;

import android.media.Image;

public class user {
    private String full;
    private String users;
    private Image profile;

    public user() {
    }

    public String getFull() {
        return full;
    }

    public void setFull(String full) {
        this.full = full;
    }

    public String getUsers() {
        return users;
    }

    public void setUsers(String users) {
        this.users = users;
    }

    public Image getProfile() {
        return profile;
    }

    public void setProfile(Image profile) {
        this.profile = profile;
    }
}

