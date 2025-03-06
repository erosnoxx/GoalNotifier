package com.erosnox.seeurun.application.contracts.usecases.goals;

import com.erosnox.seeurun.application.models.dto.UserDto;
import com.erosnox.seeurun.application.models.response.goal.GoalResponse;

import java.util.UUID;

public interface GetGoalUsecase {
    GoalResponse execute(UUID id, UUID userId, UserDto currentUser);
}
