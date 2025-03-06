package com.erosnox.seeurun.application.contracts.usecases.user;

import com.erosnox.seeurun.application.models.dto.UserDto;
import com.erosnox.seeurun.application.models.request.user.UserRequest;
import com.erosnox.seeurun.application.models.response.user.UserResponse;
import java.util.UUID;

public interface UpdateUserUsecase {
    UserResponse execute(UUID id, UserRequest request, UserDto user);
}
