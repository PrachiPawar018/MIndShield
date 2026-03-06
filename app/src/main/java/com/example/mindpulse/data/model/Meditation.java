package com.example.mindpulse.data.model;

public class Meditation {
    private String title;
    private String category;
    private String duration;
    private int imageResId;

    public Meditation(String title, String category, String duration) {
        this.title = title;
        this.category = category;
        this.duration = duration;
    }

    public String getTitle() { return title; }
    public String getCategory() { return category; }
    public String getDuration() { return duration; }
}