package com.erosnox.seeurun.application.contracts.queues;

import com.erosnox.seeurun.domain.entities.GoalEntity;

public interface GoalQueue {
    void addToQueue(GoalEntity goal);
}
