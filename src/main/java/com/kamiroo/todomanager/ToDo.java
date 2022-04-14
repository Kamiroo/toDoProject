package com.kamiroo.todomanager;

import javax.validation.constraints.NotBlank;

public class ToDo {


    private Long userId;
    @NotBlank(message = "title is mandatory")
    private String title;
    private String description;
    private StatusEnum status;
    private PriorityEnum priority;


    public ToDo(String title, String description, StatusEnum status, PriorityEnum priority, Long userId) {
        this.title = title;
        this.description = description;
        this.status = status;
        this.priority = priority;
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public PriorityEnum getPriority() {
        return priority;
    }

    public void setPriority(PriorityEnum priority) {
        this.priority = priority;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
