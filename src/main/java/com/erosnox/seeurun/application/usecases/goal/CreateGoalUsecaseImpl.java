package com.erosnox.seeurun.application.usecases.goal;

import com.erosnox.seeurun.application.contracts.gateways.GoalRepository;
import com.erosnox.seeurun.application.contracts.usecases.goals.CreateGoalUsecase;
import com.erosnox.seeurun.application.exceptions.ConflictException;
import com.erosnox.seeurun.application.mapper.GoalMapper;
import com.erosnox.seeurun.application.models.dto.UserDto;
import com.erosnox.seeurun.application.models.request.goal.GoalRequest;
import com.erosnox.seeurun.application.models.response.goal.GoalResponse;
import com.erosnox.seeurun.application.utils.UserUtils;

import java.util.UUID;

public final class CreateGoalUsecaseImpl implements CreateGoalUsecase {
    private final GoalRepository repository;

    public CreateGoalUsecaseImpl(GoalRepository repository) {
        this.repository = repository;
    }

    @Override
    public GoalResponse execute(GoalRequest request, UUID userId, UserDto currentUser) {
        UserUtils.isUserAllowed(userId, currentUser);

        repository.findByTitle(request.title(), userId).ifPresent(goal -> {
            throw new ConflictException("Title already exists");
        });

        var entity = GoalMapper.toGoal(request, userId);
        var saved = repository.save(entity);
        return GoalMapper.toResponse(saved);
    }
}
