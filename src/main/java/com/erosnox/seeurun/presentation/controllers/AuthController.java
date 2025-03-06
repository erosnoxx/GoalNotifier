package com.erosnox.seeurun.presentation.controllers;

import com.erosnox.seeurun.application.contracts.usecases.auth.LoginUsecase;
import com.erosnox.seeurun.application.contracts.usecases.auth.RegisterUsecase;
import com.erosnox.seeurun.application.models.request.auth.LoginRequest;
import com.erosnox.seeurun.application.models.request.auth.RegisterRequest;
import com.erosnox.seeurun.application.models.response.auth.LoginResponse;
import com.erosnox.seeurun.application.models.response.auth.RegisterResponse;
import com.erosnox.seeurun.presentation.schemas.ApiResponseFactory;
import com.erosnox.seeurun.presentation.schemas.common.ApiSchema;
import com.erosnox.seeurun.presentation.schemas.common.Link;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;
import java.util.UUID;

@RestController @RequestMapping("auth")
public class AuthController {
    @Autowired
    private LoginUsecase loginUsecase;
    @Autowired
    private RegisterUsecase registerUsecase;

    @PostMapping("/login")
    public ResponseEntity<ApiSchema<LoginResponse>> login(
            @RequestBody @Valid LoginRequest request) {
        var loginResponse = this.loginUsecase.execute(request);

        var links = getLinks(loginResponse.id());

        return ApiResponseFactory.ok(
                loginResponse,
                links,
                "Login successful");
    }

    @PostMapping("/register")
    public ResponseEntity<ApiSchema<RegisterResponse>> register(
            @RequestBody @Valid RegisterRequest request) {
        var registerResponse = this.registerUsecase.execute(request);

        var links = getLinks(registerResponse.id());
        var selfLink = links.get("_self");

        return ApiResponseFactory.created(
                registerResponse,
                links,
                "User created successfully",
                selfLink.href());
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
