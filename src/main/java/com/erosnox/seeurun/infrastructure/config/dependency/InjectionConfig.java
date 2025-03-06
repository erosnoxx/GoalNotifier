package com.erosnox.seeurun.infrastructure.config.dependency;

import com.erosnox.seeurun.application.contracts.gateways.UserRepository;
import com.erosnox.seeurun.application.contracts.services.CryptService;
import com.erosnox.seeurun.application.contracts.services.LoginService;
import com.erosnox.seeurun.application.contracts.usecases.auth.LoginUsecase;
import com.erosnox.seeurun.application.contracts.usecases.auth.RegisterUsecase;
import com.erosnox.seeurun.application.contracts.usecases.user.*;
import com.erosnox.seeurun.application.usecases.auth.LoginUsecaseImpl;
import com.erosnox.seeurun.application.usecases.auth.RegisterUsecaseImpl;
import com.erosnox.seeurun.application.usecases.user.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InjectionConfig {
    @Bean
    public LoginUsecase loginUsecase(LoginService loginService) {
        return new LoginUsecaseImpl(loginService);
    }

    @Bean
    public RegisterUsecase registerUsecase(
            CryptService cryptService,
            UserRepository repository) {
        return new RegisterUsecaseImpl(repository, cryptService);
    }

    @Bean
    public GetUserUsecase getUserUsecase(UserRepository repository) {
        return new GetUserUsecaseImpl(repository);
    }

    @Bean
    public GetAllUsersUsecase getAllUsersUsecase(UserRepository repository) {
        return new GetAllUsersUsecaseImpl(repository);
    }

    @Bean
    public DeactivateUserUsecase deactivateUserUsecase(UserRepository repository) {
        return new DeactivateUserUsecaseImpl(repository);
    }

    @Bean
    public ActivateUserUsecase activateUserUsecase(UserRepository repository) {
        return new ActivateUserUsecaseImpl(repository);
    }

    @Bean
    public DeleteUserUsecase deleteUserUsecase(UserRepository repository) {
        return new DeleteUserUsecaseImpl(repository);
    }

    @Bean
    public UpdateUserUsecase updateUserUsecase(UserRepository repository, CryptService cryptService) {
        return new UpdateUserUsecaseImpl(repository, cryptService);
    }

    @Bean
    public GrantAdminRoleUsecase grantAdminRoleUsecase(UserRepository repository) {
        return new GrantAdminRoleUsecaseImpl(repository);
    }
}
