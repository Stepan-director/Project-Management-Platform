package com.example.Task.dto;

import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDateTime;
import java.util.UUID;

public class CreateTaskRequest {

    private String title;

    private String description;

    private LocalDateTime deadline;

    private UUID creatorId;

    private UUID assigneeId;

    private Integer priority;

    public CreateTaskRequest(String title, String description, LocalDateTime deadline, UUID creatorId, UUID assigneeId, Integer priority) {
        this.title = title;
        this.description = description;
        this.deadline = deadline;
        this.creatorId = creatorId;
        this.assigneeId = assigneeId;
        this.priority = priority;
    }

    public CreateTaskRequest() {
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

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    public UUID getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(UUID creatorId) {
        this.creatorId = creatorId;
    }

    public UUID getAssigneeId() {
        return assigneeId;
    }

    public void setAssigneeId(UUID assigneeId) {
        this.assigneeId = assigneeId;
    }


    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }
}