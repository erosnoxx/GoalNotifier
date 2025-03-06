package com.erosnox.seeurun.application.usecases.user;

import com.erosnox.seeurun.application.contracts.gateways.UserRepository;
import com.erosnox.seeurun.application.contracts.usecases.user.GetAllUsersUsecase;
import com.erosnox.seeurun.application.mapper.UserMapper;
import com.erosnox.seeurun.application.models.response.user.UserResponse;

import java.util.List;
import java.util.stream.Collectors;

public class GetAllUsersUsecaseImpl implements GetAllUsersUsecase {
    private final UserRepository repository;

    public GetAllUsersUsecaseImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<UserResponse> execute() {
        return repository.findAll().stream().map(UserMapper::toResponse).collect(Collectors.toList());
    }
}
