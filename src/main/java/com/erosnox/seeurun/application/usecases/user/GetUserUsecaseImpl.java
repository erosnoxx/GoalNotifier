package com.erosnox.seeurun.application.usecases.user;

import com.erosnox.seeurun.application.contracts.gateways.UserRepository;
import com.erosnox.seeurun.application.contracts.usecases.user.GetUserUsecase;
import com.erosnox.seeurun.application.enums.RolesEnum;
import com.erosnox.seeurun.application.exceptions.ConflictException;
import com.erosnox.seeurun.application.exceptions.NotFoundException;
import com.erosnox.seeurun.application.exceptions.UnauthorizedException;
import com.erosnox.seeurun.application.mapper.UserMapper;
import com.erosnox.seeurun.application.models.dto.UserDto;
import com.erosnox.seeurun.application.models.response.user.UserResponse;
import com.erosnox.seeurun.application.utils.UserUtils;

import java.util.Objects;
import java.util.UUID;

public final class GetUserUsecaseImpl implements GetUserUsecase {
    private final UserRepository repository;

    public GetUserUsecaseImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserResponse execute(UUID id, UserDto currentUser) {
        UserUtils.isUserAllowed(id, currentUser);

        var entity = this.repository.findById(id)
            .orElseThrow(() -> new NotFoundException("User not found"));

        return UserMapper.toResponse(entity);
    }
}
