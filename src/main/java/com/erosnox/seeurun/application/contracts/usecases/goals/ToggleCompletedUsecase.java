package com.erosnox.seeurun.application.contracts.usecases.goals;

import com.erosnox.seeurun.application.models.dto.UserDto;
import com.erosnox.seeurun.application.models.response.goal.GoalResponse;

import java.util.UUID;

public interface ToggleCompletedUsecase {
    GoalResponse execute(UUID userId, UUID goalId, UserDto currentUser);
}
