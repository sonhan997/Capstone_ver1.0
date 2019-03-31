package com.example.nguye.capston1_dtu.Model;

public class Anh {
    private String title, image;
    // constructor
    public Anh(String title, String image) {
        this.title = title;
        this.image = image;
    }

    // non constructor
    public Anh() {
    }
    // setter vs getter
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
