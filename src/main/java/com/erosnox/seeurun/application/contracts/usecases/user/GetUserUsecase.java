package com.erosnox.seeurun.application.contracts.usecases.user;

import com.erosnox.seeurun.application.models.dto.UserDto;
import com.erosnox.seeurun.application.models.response.user.UserResponse;
import java.util.UUID;

public interface GetUserUsecase {
    UserResponse execute(UUID uuid, UserDto currentUser);
}
