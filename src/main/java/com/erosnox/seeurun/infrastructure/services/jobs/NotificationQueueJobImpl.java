package com.erosnox.seeurun.infrastructure.services.jobs;

import com.erosnox.seeurun.application.contracts.gateways.GoalRepository;
import com.erosnox.seeurun.application.contracts.services.jobs.NotificationQueueJob;
import com.erosnox.seeurun.application.contracts.services.EmailService;
import com.erosnox.seeurun.application.enums.GoalStatus;
import com.erosnox.seeurun.application.models.dto.EmailDto;
import com.erosnox.seeurun.application.models.dto.GoalDto;
import com.erosnox.seeurun.domain.entities.GoalResponse;
import com.erosnox.seeurun.infrastructure.persistence.repositories.GoalJpaRepository;
import com.erosnox.seeurun.infrastructure.persistence.repositories.UserJpaRepository;
import com.erosnox.seeurun.infrastructure.utils.EmailTemplateLoader;
import com.google.gson.Gson;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationQueueJobImpl implements NotificationQueueJob {
    @Autowired
    private EmailService emailService;
    @Autowired
    private GoalJpaRepository repository;
    @Autowired
    private UserJpaRepository userRepository;

    @Override
    @RabbitListener(queues = "notificationQueue")
    public void startListening(String message) throws Exception {
        var goal = deserializeGoal(message);
        proccessNotification(goal);
    }

    private GoalDto deserializeGoal(String message) {
        var gson = new Gson();
        return gson.fromJson(message, GoalDto.class);
    }

    private void proccessNotification(GoalDto dto) {
        var goal = repository.findById(dto.id())
                .orElseThrow(() -> new RuntimeException("Goal not found"));

        goal.setStatus(GoalStatus.FAILED);
        goal.setCompleted(false);
        repository.save(goal);

        var user = userRepository.findById(dto.userId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        var template = EmailTemplateLoader.loadTemplate("goal-failure.html");
        var body = EmailTemplateLoader.processTemplate(template, goal.getTitle());

        emailService.sendEmail(new EmailDto(user.getEmail(), "Sua meta falhou :(", body));
    }
}
