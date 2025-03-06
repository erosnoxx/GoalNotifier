package com.erosnox.seeurun.application.usecases.user;

import com.erosnox.seeurun.application.contracts.gateways.UserRepository;
import com.erosnox.seeurun.application.contracts.usecases.user.DeleteUserUsecase;
import com.erosnox.seeurun.application.exceptions.BadRequestException;

import java.util.UUID;

public class DeleteUserUsecaseImpl implements DeleteUserUsecase {
    private final UserRepository repository;

    public DeleteUserUsecaseImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public void execute(UUID id) {
        var entity = repository.findById(id).orElseThrow(() -> new BadRequestException("User not found"));
        repository.delete(entity);
    }
}
