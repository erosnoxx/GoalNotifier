package com.erosnox.seeurun.application.models.request.user;

import com.erosnox.seeurun.application.enums.RolesEnum;

import java.util.Optional;

public record UserRequest(
        Optional<String> username,
        Optional<String> password
) {
}
