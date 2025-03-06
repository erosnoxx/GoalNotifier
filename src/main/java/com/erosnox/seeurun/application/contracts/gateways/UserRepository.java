package com.erosnox.seeurun.application.contracts.gateways;

import com.erosnox.seeurun.domain.entities.UserEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository {
    UserEntity save(UserEntity user);
    UserEntity update(UserEntity user);
    Optional<UserEntity> findById(UUID id);
    List<UserEntity> insertAll(List<UserEntity> users);
    Optional<UserEntity> findByUsername(String username);
    List<UserEntity> findAll();
    void delete(UserEntity user);
}
