package com.erosnox.seeurun.domain.entities;

import com.erosnox.seeurun.application.enums.NotificationType;
import com.erosnox.seeurun.domain.entities.common.BaseEntity;

import java.time.LocalDateTime;
import java.util.UUID;

public class NotificationEntity extends BaseEntity<UUID> {
    private String message;
    private NotificationType type;
    private LocalDateTime sentAt;

    private UUID userId;
    private UUID goalId;

    public NotificationEntity(
            String message,
            NotificationType type,
            LocalDateTime sentAt,
            UUID userId,
            UUID goalId) {
        setMessage(message);
        setType(type);
        setSentAt(sentAt);
        setUserId(userId);
        setGoalId(goalId);
    }

    public void setMessage(String message) {
        if (message == null || message.isEmpty()) {
            throw new IllegalArgumentException("Message cannot be null or empty");
        }

        if (message.length() > 255) {
            throw new IllegalArgumentException("Message length must be less than 255 characters");
        }

        this.message = message;
    }

    public void setType(NotificationType type) {
        if (type == null) {
            throw new IllegalArgumentException("Notification type cannot be null");
        }

        this.type = type;
    }

    public void setSentAt(LocalDateTime sentAt) {
        if (sentAt == null) {
            throw new IllegalArgumentException("SentAt cannot be null");
        }

        if (sentAt.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("SentAt cannot be in the past");
        }

        this.sentAt = sentAt;
    }

    public void setUserId(UUID userId) {
        if (userId == null) {
            throw new IllegalArgumentException("User ID cannot be null");
        }

        this.userId = userId;
    }

    public void setGoalId(UUID goalId) {
        if (goalId == null) {
            throw new IllegalArgumentException("Goal ID cannot be null");
        }

        this.goalId = goalId;
    }

    public String getMessage() {
        return message;
    }

    public NotificationType getType() {
        return type;
    }

    public LocalDateTime getSentAt() {
        return sentAt;
    }

    public UUID getUserId() {
        return userId;
    }

    public UUID getGoalId() {
        return goalId;
    }
}
