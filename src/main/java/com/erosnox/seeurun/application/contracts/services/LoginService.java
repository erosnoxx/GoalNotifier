package com.erosnox.seeurun.application.contracts.services;

import com.erosnox.seeurun.application.models.request.auth.LoginRequest;
import com.erosnox.seeurun.application.models.response.auth.LoginResponse;

public interface LoginService {
    LoginResponse login(LoginRequest loginRequest);
}
