package com.erosnox.seeurun.application.contracts.usecases.goals;

import com.erosnox.seeurun.application.enums.GoalStatus;
import com.erosnox.seeurun.application.models.dto.UserDto;
import com.erosnox.seeurun.application.models.response.goal.GoalResponse;

import java.util.List;
import java.util.UUID;

public interface GetAllByStatusUsecase {
    List<GoalResponse> execute(UUID userId, GoalStatus status, UserDto currentUser);
}
