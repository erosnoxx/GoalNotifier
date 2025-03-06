package com.erosnox.seeurun.application.usecases.auth;

import com.erosnox.seeurun.application.contracts.gateways.UserRepository;
import com.erosnox.seeurun.application.contracts.services.CryptService;
import com.erosnox.seeurun.application.contracts.usecases.auth.RegisterUsecase;
import com.erosnox.seeurun.application.exceptions.ConflictException;
import com.erosnox.seeurun.application.mapper.UserMapper;
import com.erosnox.seeurun.application.models.request.auth.RegisterRequest;
import com.erosnox.seeurun.application.models.response.auth.RegisterResponse;

public class RegisterUsecaseImpl implements RegisterUsecase {
    private final UserRepository repository;
    private final CryptService cryptService;

    public RegisterUsecaseImpl(
            UserRepository repository,
            CryptService cryptService) {
        this.repository = repository;
        this.cryptService = cryptService;
    }

    @Override
    public RegisterResponse execute(RegisterRequest request) {
        if (repository.findByUsername(request.username()).isPresent()) {
            throw new ConflictException("username already in use");
        }

        var passwordHash = this.cryptService.encrypt(request.password());
        var user = UserMapper.toEntity(request, passwordHash);

        return UserMapper.toRegisterResponse(this.repository.save(user));
    }
}
