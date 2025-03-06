package com.erosnox.seeurun.application.usecases.user;

import com.erosnox.seeurun.application.contracts.gateways.UserRepository;
import com.erosnox.seeurun.application.contracts.usecases.user.ActivateUserUsecase;
import com.erosnox.seeurun.application.exceptions.ConflictException;
import com.erosnox.seeurun.application.exceptions.NotFoundException;
import com.erosnox.seeurun.application.models.dto.UserDto;
import com.erosnox.seeurun.application.utils.UserUtils;

import java.util.UUID;

public final class ActivateUserUsecaseImpl implements ActivateUserUsecase {
    private final UserRepository repository;

    public ActivateUserUsecaseImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public void execute(UUID id, UserDto currentUser) {
        UserUtils.isUserAllowed(id, currentUser);

        var entity = repository.findById(id).orElseThrow(() -> new NotFoundException("User not found"));
        if (entity.isActive()) {
            throw new ConflictException("User is already activated");
        }

        entity.setActive(true);
        repository.update(entity);
    }
}
