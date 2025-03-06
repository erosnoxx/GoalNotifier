package com.erosnox.seeurun.infrastructure.config.security;

import com.erosnox.seeurun.application.models.dto.UserDto;
import com.erosnox.seeurun.infrastructure.persistence.entities.UserJpaEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class SecurityUtils {

    public static UserDto getCurrentUser() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !(authentication.getPrincipal() instanceof UserJpaEntity)) {
            throw new IllegalStateException("Usuário não autenticado ou token inválido");
        }

        var user = (UserJpaEntity) authentication.getPrincipal();
        return new UserDto(user.getId(), user.getRole());
    }
}
