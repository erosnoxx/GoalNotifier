package com.erosnox.seeurun.application.contracts.usecases.goals;

import com.erosnox.seeurun.application.models.dto.UserDto;
import com.erosnox.seeurun.application.models.request.goal.GoalRequest;
import com.erosnox.seeurun.application.models.response.goal.GoalResponse;

import java.util.UUID;

public interface CreateGoalUsecase {
    GoalResponse execute(GoalRequest request, UUID userId, UserDto currentUser);
}
