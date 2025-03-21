package com.blogproject.core.models;

public class NavItems {
    private String title;
    private String link;
    private String hideNavProperty;

    public NavItems(String title, String link, String hideNavProperty) {
        this.title = title;
        this.link = link;
        this.hideNavProperty = hideNavProperty;
    }
    public String getTitle() {
        return title;
    }
    public String getLink() {
        return link;
    }
    public String getHideNavProperty() {
        return hideNavProperty;
    }
}
