package com.erosnox.seeurun.application.usecases.goal;

import com.erosnox.seeurun.application.contracts.gateways.GoalRepository;
import com.erosnox.seeurun.application.contracts.usecases.goals.ToggleCompletedUsecase;
import com.erosnox.seeurun.application.exceptions.NotFoundException;
import com.erosnox.seeurun.application.mapper.GoalMapper;
import com.erosnox.seeurun.application.models.dto.UserDto;
import com.erosnox.seeurun.application.models.response.goal.GoalResponse;
import com.erosnox.seeurun.application.utils.UserUtils;

import java.util.UUID;

public final class ToggleCompletedUsecaseImpl implements ToggleCompletedUsecase {
    private final GoalRepository repository;

    public ToggleCompletedUsecaseImpl(GoalRepository repository) {
        this.repository = repository;
    }

    @Override
    public GoalResponse execute(UUID userId, UUID goalId, UserDto currentUser) {
        UserUtils.isUserAllowed(userId, currentUser);

        var entity = repository.findById(goalId, userId).orElseThrow(
                () -> new NotFoundException("Goal not found"));

        entity.setCompleted(!entity.isCompleted());

        var updated = repository.update(entity);

        return GoalMapper.toResponse(updated);
    }
}
