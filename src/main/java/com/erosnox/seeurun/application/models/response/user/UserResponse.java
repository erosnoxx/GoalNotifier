package com.erosnox.seeurun.application.models.response.user;

import com.erosnox.seeurun.application.enums.RolesEnum;

import java.util.UUID;

public record UserResponse(
        UUID id,
        String username,
        String email,
        RolesEnum role,
        boolean isActive) {
}
