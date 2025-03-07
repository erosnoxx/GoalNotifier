package com.erosnox.seeurun.infrastructure.config.rabbit;

import com.erosnox.seeurun.application.enums.QueueName;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    @Value("${spring.rabbitmq.template.exchange}")
    private String EXCHANGE;

    @Value("${spring.rabbitmq.template.routing-key}")
    private String ROUTING_KEY;

    @Bean
    public Queue notificationQueue() {
        return new Queue(QueueName.NOTIFICATION_QUEUE.getQueueName(), true);
    }

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(EXCHANGE);
    }

    @Bean
    public Binding notificationBinding(Queue notificationQueue, DirectExchange exchange) {
        return BindingBuilder.bind(notificationQueue)
                .to(exchange)
                .with(ROUTING_KEY);
    }
}
