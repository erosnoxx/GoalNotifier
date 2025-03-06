package com.erosnox.seeurun.application.contracts.usecases.user;

import com.erosnox.seeurun.application.models.dto.UserDto;
import java.util.UUID;

public interface ActivateUserUsecase {
    void execute(UUID id, UserDto currentUser);
}
