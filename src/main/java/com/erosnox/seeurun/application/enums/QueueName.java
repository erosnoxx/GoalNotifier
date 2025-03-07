package com.erosnox.seeurun.application.enums;

public enum QueueName {
    NOTIFICATION_QUEUE("notificationQueue");

    private final String queueName;

    QueueName(String queueName) {
        this.queueName = queueName;
    }

    public String getQueueName() {
        return queueName;
    }
}

