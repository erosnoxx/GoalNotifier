package com.erosnox.seeurun.application.usecases.user;

import com.erosnox.seeurun.application.contracts.gateways.UserRepository;
import com.erosnox.seeurun.application.contracts.usecases.user.DeactivateUserUsecase;
import com.erosnox.seeurun.application.exceptions.BadRequestException;
import com.erosnox.seeurun.application.exceptions.ConflictException;
import com.erosnox.seeurun.application.models.dto.UserDto;
import com.erosnox.seeurun.application.utils.UserUtils;

import java.util.UUID;

public final class DeactivateUserUsecaseImpl implements DeactivateUserUsecase {
    private final UserRepository repository;

    public DeactivateUserUsecaseImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public void execute(UUID id, UserDto currentUser) {
        UserUtils.isUserAllowed(id, currentUser);

        var entity = repository.findById(id).orElseThrow(() -> new BadRequestException("User not found"));
        if (!entity.isActive()) {
            throw new ConflictException("User is already unactivated");
        }

        entity.setActive(false);
        repository.update(entity);
    }
}
