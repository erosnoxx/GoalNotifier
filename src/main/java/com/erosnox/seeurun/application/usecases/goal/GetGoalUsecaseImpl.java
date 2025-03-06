package com.erosnox.seeurun.application.usecases.goal;

import com.erosnox.seeurun.application.contracts.gateways.GoalRepository;
import com.erosnox.seeurun.application.contracts.usecases.goals.GetGoalUsecase;
import com.erosnox.seeurun.application.exceptions.NotFoundException;
import com.erosnox.seeurun.application.mapper.GoalMapper;
import com.erosnox.seeurun.application.models.dto.UserDto;
import com.erosnox.seeurun.application.models.response.goal.GoalResponse;
import com.erosnox.seeurun.application.utils.UserUtils;

import java.util.UUID;

public final class GetGoalUsecaseImpl implements GetGoalUsecase {
    private final GoalRepository repository;

    public GetGoalUsecaseImpl(GoalRepository repository) {
        this.repository = repository;
    }

    @Override
    public GoalResponse execute(UUID id, UUID userId, UserDto currentUser) {
        UserUtils.isUserAllowed(userId, currentUser);

        var entity = repository.findById(id, userId).orElseThrow(
                () -> new NotFoundException("Goal not found"));

        return GoalMapper.toResponse(entity);
    }
}
