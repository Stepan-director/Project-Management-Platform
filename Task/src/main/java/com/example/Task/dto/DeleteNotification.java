package com.example.Task.dto;

public class DeleteNotification {

    private String title;

    public DeleteNotification() {
    }

    public DeleteNotification(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
