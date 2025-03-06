package com.erosnox.seeurun.application.mapper;

import com.erosnox.seeurun.application.models.request.goal.GoalRequest;
import com.erosnox.seeurun.application.models.response.goal.GoalResponse;
import com.erosnox.seeurun.domain.entities.GoalEntity;

import java.util.UUID;

public class GoalMapper {
    public static GoalResponse toResponse(GoalEntity goal) {
        return new GoalResponse(
                goal.getId(),
                goal.getTitle(),
                goal.getDescription(),
                goal.getTargetDateTime(),
                goal.isCompleted());
    }

    public static GoalEntity toGoal(GoalRequest request, UUID userId) {
        return new GoalEntity(
                request.title(),
                request.description(),
                request.targetDateTime(),
                userId
        );
    }
}
