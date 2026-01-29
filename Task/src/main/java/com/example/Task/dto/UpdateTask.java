package com.example.Task.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public class UpdateTask {

    private String title;

    private String description;

    private String status;

    private LocalDateTime deadline;

    private UUID creatorId; // другой тип

    private UUID assigneeId;

    private Integer priority;

    private LocalDateTime updatedAt;  // дата изменения


    public UpdateTask(String title, String description, String status, LocalDateTime deadline, UUID creatorId, UUID assigneeId, Integer priority, LocalDateTime updatedAt) {
        this.title = title;
        this.description = description;
        this.status = status;
        this.deadline = deadline;
        this.creatorId = creatorId;
        this.assigneeId = assigneeId;
        this.priority = priority;
        this.updatedAt = updatedAt;
    }

    public UpdateTask() {
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

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
