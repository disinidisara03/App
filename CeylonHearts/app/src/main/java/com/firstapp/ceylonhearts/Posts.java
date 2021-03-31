package com.firstapp.ceylonhearts;

public class Posts {
    private String charity_image,description;
    private String username;

    public Posts()
    {

    }

    public Posts(String charity_image, String description) {
        this.charity_image = charity_image;
        this.description = description;
    }

    public String getCharity_image() {
        return charity_image;
    }

    public void setCharity_image(String charity_image) {
        this.charity_image = charity_image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
