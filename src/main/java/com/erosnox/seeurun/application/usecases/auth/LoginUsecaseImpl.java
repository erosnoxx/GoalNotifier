package com.erosnox.seeurun.application.usecases.auth;

import com.erosnox.seeurun.application.contracts.services.LoginService;
import com.erosnox.seeurun.application.contracts.usecases.auth.LoginUsecase;
import com.erosnox.seeurun.application.models.request.auth.LoginRequest;
import com.erosnox.seeurun.application.models.response.auth.LoginResponse;

public class LoginUsecaseImpl implements LoginUsecase {
    private final LoginService loginService;

    public LoginUsecaseImpl(LoginService loginService) {
        this.loginService = loginService;
    }

    @Override
    public LoginResponse execute(LoginRequest request) {
        return loginService.login(request);
    }
}
