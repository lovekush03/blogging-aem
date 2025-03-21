package com.blogproject.core.models;

public class Blogs {
    String title;
    String path;
    String date;
    String thumbnail;
    String description;

    public Blogs(String title, String path, String date, String thumbnail,String description) {
        this.title = title;
        this.path = path;
        this.date = date;
        this.thumbnail = thumbnail;
        this.description = description;
    }
    public String getTitle() {
        return this.title;
    }
    public String getPath() {
        return this.path;
    }
    public String getDate() {
        return this.date;
    }
    public String getThumbnail() {
        return this.thumbnail;
    }
    public String getDescription() {
        return this.description;
    }
}
