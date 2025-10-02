package com.example.taskmanager.model;

public class Task {
    private int id;
    private String title;
    private String status;
    private String description;

    public Task() {}

    public Task(String title, String status, String description) {
        this.title = title;
        this.status = status;
        this.description = description;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}