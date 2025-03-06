package com.erosnox.seeurun.application.utils;

import com.erosnox.seeurun.application.enums.RolesEnum;
import com.erosnox.seeurun.application.exceptions.UnauthorizedException;
import com.erosnox.seeurun.application.models.dto.UserDto;
import java.util.Objects;
import java.util.UUID;

public class UserUtils {
    public static void isUserAllowed(UUID id, UserDto currentUser) {
        if (Objects.equals(currentUser.id(), id) || RolesEnum.ADMIN.equals(currentUser.role())) {
            return;
        }

        throw new UnauthorizedException("You are not the owner of this account " +
                "and cannot access this information.");
    }
}
