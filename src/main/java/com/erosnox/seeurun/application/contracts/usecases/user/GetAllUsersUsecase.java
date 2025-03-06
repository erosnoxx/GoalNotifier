package com.erosnox.seeurun.application.contracts.usecases.user;

import com.erosnox.seeurun.application.models.response.user.UserResponse;

import java.util.List;

public interface GetAllUsersUsecase {
    List<UserResponse> execute();
}
