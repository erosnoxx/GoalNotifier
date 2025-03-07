package com.erosnox.seeurun.application.contracts.services.queues;


import com.erosnox.seeurun.application.models.dto.GoalDto;
import com.erosnox.seeurun.application.models.response.goal.GoalResponse;

public interface NotificationQueue {
    void addToQueue(GoalDto goal);
}
