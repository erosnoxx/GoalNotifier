package com.erosnox.seeurun.infrastructure.mappers;

import com.erosnox.seeurun.domain.entities.UserEntity;
import com.erosnox.seeurun.infrastructure.persistence.entities.UserJpaEntity;

public class UserJpaMapper {
    public static UserJpaEntity toJpa(UserEntity user) {
        var entity = new UserJpaEntity();
        entity.setUsername(user.getUsername());
        entity.setPasswordHash(user.getPasswordHash());
        entity.setRole(user.getRole());
        entity.setActive(user.isActive());
        entity.setEmail(user.getEmail());

        return entity;
    }

    public static UserEntity toEntity(UserJpaEntity userJpaEntity) {
        var entity = new UserEntity(
                userJpaEntity.getUsername(),
                userJpaEntity.getPasswordHash(),
                userJpaEntity.getEmail(),
                userJpaEntity.getRole()
        );

        entity.setId(userJpaEntity.getId());

        return entity;
    }
}
