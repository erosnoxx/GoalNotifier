package com.erosnox.seeurun.infrastructure.services.scheduler;

import com.erosnox.seeurun.application.contracts.gateways.GoalRepository;
import com.erosnox.seeurun.application.contracts.services.queues.NotificationQueue;
import com.erosnox.seeurun.application.contracts.services.scheduler.GoalScheduler;
import com.erosnox.seeurun.application.mapper.GoalMapper;
import com.erosnox.seeurun.application.mapper.UserMapper;
import com.erosnox.seeurun.application.models.dto.GoalDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class GoalSchedulerImpl implements GoalScheduler {
    @Autowired
    private GoalRepository repository;
    @Autowired
    private NotificationQueue notificationQueue;
    private static final Logger logger = LoggerFactory.getLogger(GoalSchedulerImpl.class);


    @Override
    @Scheduled(fixedRate = 60000)
    public void checkGoals() {
        var goals = repository.findAll();

        for (var goal : goals) {

            if (goal.hasPassedTargetDateTime() && goal.getStatus().isPending()) {
                logger.info("Meta pendente encontrada e será enviada para a fila: {}", goal.getId());
                notificationQueue.addToQueue(new GoalDto(goal.getId(), goal.getUserId()));
            }
        }
    }
}
