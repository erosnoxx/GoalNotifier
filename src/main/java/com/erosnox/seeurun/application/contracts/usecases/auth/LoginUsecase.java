package com.erosnox.seeurun.application.contracts.usecases.auth;

import com.erosnox.seeurun.application.models.request.auth.LoginRequest;
import com.erosnox.seeurun.application.models.response.auth.LoginResponse;

public interface LoginUsecase {
    LoginResponse execute(LoginRequest request);
}
