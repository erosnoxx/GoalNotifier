package com.erosnox.seeurun.application.contracts.services.jobs;

public interface NotificationQueueJob {
    void startListening(String message) throws Exception;
}
