package com.erosnox.seeurun.application.usecases.goal;

import com.erosnox.seeurun.application.contracts.gateways.GoalRepository;
import com.erosnox.seeurun.application.contracts.usecases.goals.GetAllByStatusUsecase;
import com.erosnox.seeurun.application.enums.GoalStatus;
import com.erosnox.seeurun.application.mapper.GoalMapper;
import com.erosnox.seeurun.application.models.dto.UserDto;
import com.erosnox.seeurun.application.models.response.goal.GoalResponse;
import com.erosnox.seeurun.application.utils.UserUtils;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public final class GetAllByStatusUsecaseImpl implements GetAllByStatusUsecase {
    private final GoalRepository repository;

    public GetAllByStatusUsecaseImpl(GoalRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<GoalResponse> execute(UUID userId, GoalStatus status, UserDto currentUser) {
        UserUtils.isUserAllowed(userId, currentUser);

        return repository.findAllByStatus(userId, status).stream()
                .map(GoalMapper::toResponse).collect(Collectors.toList());
    }
}
