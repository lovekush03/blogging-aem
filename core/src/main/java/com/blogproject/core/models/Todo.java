package com.blogproject.core.models;

public class Todo {
    private int userId;
    private int id;
    private String title;
    private Boolean status;

    public Todo(int uid, int id, String title, Boolean status){
        this.userId = uid;
        this.id = id;
        this.title = title;
        this.status = status;
    }
    public int getUserId(){
        return this.userId;
    }

    public int getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public Boolean getStatus() {
        return this.status;
    }
}
