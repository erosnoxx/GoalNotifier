package com.erosnox.seeurun.domain.entities;

import com.erosnox.seeurun.application.enums.GoalStatus;
import com.erosnox.seeurun.domain.entities.common.BaseEntity;

import java.time.LocalDateTime;
import java.util.UUID;

public class GoalResponse extends BaseEntity<UUID> {
    private String title;
    private String description;
    private LocalDateTime targetDateTime;
    private GoalStatus status;
    private boolean completed;

    private UUID userId;

    public boolean hasPassedTargetDateTime() {
        return targetDateTime.isBefore(LocalDateTime.now());
    }

    public GoalResponse(
            String title,
            String description,
            LocalDateTime targetDateTime,
            UUID userId) {
        setTitle(title);
        setDescription(description);
        setTargetDateTime(targetDateTime);
        setStatus(GoalStatus.PENDING);
        setCompleted(false);
        setUserId(userId);
    }

    public void setTitle(String title) {
        if (title == null || title.isEmpty()) {
            throw new IllegalArgumentException("Title cannot be null or empty");
        }

        if (title.length() > 50 || title.length() < 3) {
            throw new IllegalArgumentException("Title length must be between 3 and 50 characters");
        }

        this.title = title;
    }

    public void setDescription(String description) {
        if (description == null || description.isEmpty()) {
            throw new IllegalArgumentException("Description cannot be null or empty");
        }

        if (description.length() > 255 || description.length() < 3) {
            throw new IllegalArgumentException("Description length must be between 3 and 255 characters");
        }

        this.description = description;
    }

    public void setTargetDateTime(LocalDateTime targetDateTime) {
        if (targetDateTime == null) {
            throw new IllegalArgumentException("TargetDateTime cannot be null");
        }

        this.targetDateTime = targetDateTime;
    }

    public void setStatus(GoalStatus status) {
        if (status == null) {
            throw new IllegalArgumentException("Status cannot be null");
        }
        this.status = status;
    }

    public void setCompleted(boolean completed) {
        if (completed != this.completed) {
            this.completed = completed;
        }

        this.completed = completed;
    }

    public void setUserId(UUID userId) {
        if (userId == null) {
            throw new IllegalArgumentException("UserId cannot be null");
        }

        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getTargetDateTime() {
        return targetDateTime;
    }

    public boolean isCompleted() {
        return completed;
    }

    public UUID getUserId() {
        return userId;
    }

    public GoalStatus getStatus() {
        return status;
    }
}
