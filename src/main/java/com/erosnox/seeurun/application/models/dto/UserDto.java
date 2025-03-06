package com.erosnox.seeurun.application.models.dto;

import com.erosnox.seeurun.application.enums.RolesEnum;

import java.util.UUID;

public record UserDto(UUID id, RolesEnum role) {
}
