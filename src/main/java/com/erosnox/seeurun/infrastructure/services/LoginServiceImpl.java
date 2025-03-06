package com.erosnox.seeurun.infrastructure.services;

import com.erosnox.seeurun.application.contracts.services.LoginService;
import com.erosnox.seeurun.application.models.request.auth.LoginRequest;
import com.erosnox.seeurun.application.models.response.auth.LoginResponse;
import com.erosnox.seeurun.infrastructure.config.authorization.TokenService;
import com.erosnox.seeurun.infrastructure.persistence.entities.UserJpaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public LoginResponse login(LoginRequest request) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(
                request.username(), request.password());

        var auth = this.authenticationManager.authenticate(usernamePassword);

        Map<String, Object> tokenResponse = this.tokenService
                .generateToken((UserJpaEntity) auth.getPrincipal());

        String token = (String) tokenResponse.get("token");

        return new LoginResponse(token, ((UserJpaEntity) auth.getPrincipal()).getId());
    }
}
