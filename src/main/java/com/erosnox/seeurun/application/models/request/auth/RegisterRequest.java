package com.erosnox.seeurun.application.models.request.auth;

import com.erosnox.seeurun.application.enums.RolesEnum;

public record RegisterRequest(
        String username,
        String password,
        String email,
        RolesEnum role) {
}
