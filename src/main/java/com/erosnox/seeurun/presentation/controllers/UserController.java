package com.erosnox.seeurun.presentation.controllers;

import com.erosnox.seeurun.application.contracts.usecases.user.*;
import com.erosnox.seeurun.application.models.request.user.UserRequest;
import com.erosnox.seeurun.application.models.response.user.UserResponse;
import com.erosnox.seeurun.infrastructure.config.security.SecurityUtils;
import com.erosnox.seeurun.presentation.schemas.ApiResponseFactory;
import com.erosnox.seeurun.presentation.schemas.common.ApiSchema;
import com.erosnox.seeurun.presentation.schemas.common.Link;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController @RequestMapping("users")
public class UserController {
    @Autowired
    private GetUserUsecase getUserUsecase;
    @Autowired
    private GetAllUsersUsecase getAllUsersUsecase;
    @Autowired
    private UpdateUserUsecase updateUserUsecase;
    @Autowired
    private DeactivateUserUsecase deactivateUserUsecase;
    @Autowired
    private ActivateUserUsecase activateUserUsecase;
    @Autowired
    private GrantAdminRoleUsecase grantAdminRoleUsecase;

    @GetMapping("/")
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        return ResponseEntity.ok(getAllUsersUsecase.execute());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiSchema<UserResponse>> getUser(@PathVariable UUID id) {
        var currentUser = SecurityUtils.getCurrentUser();
        var response = getUserUsecase.execute(id, currentUser);
        return ApiResponseFactory.ok(response, getLinks(response.id()), "User created successfully");
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiSchema<UserResponse>> updateUser(
            @PathVariable UUID id,
            @RequestBody @Valid UserRequest request) {
        var currentUser = SecurityUtils.getCurrentUser();
        var response = updateUserUsecase.execute(id, request, currentUser);

        return ApiResponseFactory.ok(response,
                getLinks(response.id()), "User updated successfully");
    }

    @PatchMapping("/{id}/deactivate")
    public ResponseEntity deactivateUser(@PathVariable UUID id) {
        var currentUser = SecurityUtils.getCurrentUser();
        deactivateUserUsecase.execute(id, currentUser);
        return ResponseEntity.ok().body(null);
    }

    @PatchMapping("/{id}/activate")
    public ResponseEntity activateUser(@PathVariable UUID id) {
        var currentUser = SecurityUtils.getCurrentUser();
        activateUserUsecase.execute(id, currentUser);
        return ResponseEntity.ok().body(null);
    }

    @PatchMapping("/{id}/grantAdmin")
    public ResponseEntity grantAdmin(@PathVariable UUID id) {
        var currentUser = SecurityUtils.getCurrentUser();
        grantAdminRoleUsecase.execute(id, currentUser);
        return ResponseEntity.ok().body(null);
    }

    private Map<String, Link> getLinks(UUID id) {
        var selfLink = new Link("/users/" + id, "GET");
        var deleteLink = new Link("/users/" + id, "DELETE");
        var updateLink = new Link("/users/" + id, "PATCH");

        return Map.of(
                "_self", selfLink,
                "delete", deleteLink,
                "update", updateLink
        );
    }
}
