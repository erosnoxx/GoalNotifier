package com.erosnox.seeurun.application.contracts.usecases.auth;

import com.erosnox.seeurun.application.models.request.auth.RegisterRequest;
import com.erosnox.seeurun.application.models.response.auth.RegisterResponse;

public interface RegisterUsecase {
    RegisterResponse execute(RegisterRequest request);
}
