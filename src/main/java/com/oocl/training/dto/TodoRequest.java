package com.oocl.training.dto;

public class TodoRequest {
    private String title;
    private String status;


    public TodoRequest() {

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
