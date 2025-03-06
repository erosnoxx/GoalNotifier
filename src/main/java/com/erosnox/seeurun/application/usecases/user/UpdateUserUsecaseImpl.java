package com.erosnox.seeurun.application.usecases.user;

import com.erosnox.seeurun.application.contracts.gateways.UserRepository;
import com.erosnox.seeurun.application.contracts.services.CryptService;
import com.erosnox.seeurun.application.contracts.usecases.user.UpdateUserUsecase;
import com.erosnox.seeurun.application.exceptions.NotFoundException;
import com.erosnox.seeurun.application.mapper.UserMapper;
import com.erosnox.seeurun.application.models.dto.UserDto;
import com.erosnox.seeurun.application.models.request.user.UserRequest;
import com.erosnox.seeurun.application.models.response.user.UserResponse;
import com.erosnox.seeurun.application.utils.UserUtils;

import java.util.UUID;

public final class UpdateUserUsecaseImpl implements UpdateUserUsecase {
    private final UserRepository repository;
    private final CryptService cryptService;

    public UpdateUserUsecaseImpl(
            UserRepository repository,
            CryptService cryptService) {
        this.repository = repository;
        this.cryptService = cryptService;
    }

    @Override
    public UserResponse execute(UUID id, UserRequest request, UserDto currentUser) {
        UserUtils.isUserAllowed(id, currentUser);

        var entity = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found"));

        request.username().ifPresent(entity::setUsername);
        request.password().ifPresent(password ->
                entity.setPasswordHash(cryptService.encrypt(password)));

        var updated = repository.update(entity);

        return UserMapper.toResponse(updated);
    }
}
