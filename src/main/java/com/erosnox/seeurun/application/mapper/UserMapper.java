package com.erosnox.seeurun.application.mapper;

import com.erosnox.seeurun.application.models.request.auth.RegisterRequest;
import com.erosnox.seeurun.application.models.response.auth.RegisterResponse;
import com.erosnox.seeurun.application.models.response.user.UserResponse;
import com.erosnox.seeurun.domain.entities.UserEntity;

public class UserMapper {
    public static UserResponse toResponse(UserEntity user) {
        return new UserResponse(
                user.getId(),
                user.getUsername(),
                user.getRole(),
                user.isActive());
    }

    public static UserEntity toEntity(RegisterRequest request, String passwordHash) {
        return new UserEntity(
                request.username(),
                passwordHash,
                request.role()
        );
    }

    public static RegisterResponse toRegisterResponse(UserEntity user) {
        return new RegisterResponse(user.getId());
    }
}
