package com.erosnox.seeurun.infrastructure.gateways;

import com.erosnox.seeurun.application.contracts.gateways.UserRepository;
import com.erosnox.seeurun.application.exceptions.NotFoundException;
import com.erosnox.seeurun.domain.entities.UserEntity;
import com.erosnox.seeurun.infrastructure.mappers.UserJpaMapper;
import com.erosnox.seeurun.infrastructure.persistence.repositories.UserJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class UserRepositoryImpl implements UserRepository {
    @Autowired
    private UserJpaRepository repository;

    @Override
    public UserEntity save(UserEntity user) {
        var jpaEntity = UserJpaMapper.toJpa(user);
        var saved = repository.save(jpaEntity);
        return UserJpaMapper.toEntity(saved);
    }

    @Override
    public UserEntity update(UserEntity user) {
        var jpaEntity = UserJpaMapper.toJpa(user);
        jpaEntity.setId(user.getId());
        var saved = repository.save(jpaEntity);
        return UserJpaMapper.toEntity(saved);
    }

    @Override
    public Optional<UserEntity> findById(UUID id) {
        return this.repository.findById(id).map(UserJpaMapper::toEntity);
    }


    @Override
    public List<UserEntity> insertAll(List<UserEntity> users) {
        var entities = repository.findAll();
        return entities.stream().map(UserJpaMapper::toEntity)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<UserEntity> findByUsername(String username) {
        return repository.findEntityByUsername(username)
                .map(UserJpaMapper::toEntity);
    }

    @Override
    public List<UserEntity> findAll() {
        return repository.findAll().stream()
                .map(UserJpaMapper::toEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(UserEntity user) {
        repository.delete(UserJpaMapper.toJpa(user));
    }
}
