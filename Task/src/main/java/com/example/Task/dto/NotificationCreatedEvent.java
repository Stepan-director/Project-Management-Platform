package com.example.Task.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public class NotificationCreatedEvent {

    private String title;

    private LocalDateTime deadline;

    private UUID creatorId;

    private UUID assigneeId;

    private LocalDateTime updatedAt;

    public NotificationCreatedEvent(String title, LocalDateTime deadline, UUID creatorId, UUID assigneeId, LocalDateTime updatedAt) {
        this.title = title;
        this.deadline = deadline;
        this.creatorId = creatorId;
        this.assigneeId = assigneeId;
        this.updatedAt = updatedAt;
    }

    public NotificationCreatedEvent() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
