package com.erosnox.seeurun.application.mapper;

import com.erosnox.seeurun.application.models.request.goal.GoalRequest;
import com.erosnox.seeurun.domain.entities.GoalResponse;

import java.util.UUID;

public class GoalMapper {
    public static com.erosnox.seeurun.application.models.response.goal.GoalResponse toResponse(GoalResponse goal) {
        return new com.erosnox.seeurun.application.models.response.goal.GoalResponse(
                goal.getId(),
                goal.getTitle(),
                goal.getDescription(),
                goal.getTargetDateTime(),
                goal.getStatus(),
                goal.isCompleted());
    }

    public static GoalResponse toGoal(GoalRequest request, UUID userId) {
        return new GoalResponse(
                request.title(),
                request.description(),
                request.targetDateTime(),
                userId);
    }
}
