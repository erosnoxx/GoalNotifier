package com.erosnox.seeurun.infrastructure.services.queues;

import com.erosnox.seeurun.application.contracts.services.queues.NotificationQueue;
import com.erosnox.seeurun.application.models.dto.GoalDto;
import com.erosnox.seeurun.application.models.response.goal.GoalResponse;
import com.google.gson.Gson;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class NotificationQueueImpl implements NotificationQueue {

    private final AmqpTemplate amqpTemplate;

    @Value("${spring.rabbitmq.template.exchange}")
    private String exchange;

    @Value("${spring.rabbitmq.template.routing-key}")
    private String routingKey;

    public NotificationQueueImpl(AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }

    @Override
    public void addToQueue(GoalDto goal) {
        String goalJson = new Gson().toJson(goal);

        amqpTemplate.convertAndSend(exchange, routingKey, goalJson);
    }
}
