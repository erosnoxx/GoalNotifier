package com.erosnox.seeurun.infrastructure.gateways;

import com.erosnox.seeurun.application.contracts.gateways.GoalRepository;
import com.erosnox.seeurun.application.enums.GoalStatus;
import com.erosnox.seeurun.domain.entities.GoalResponse;
import com.erosnox.seeurun.infrastructure.mappers.GoalJpaMapper;
import com.erosnox.seeurun.infrastructure.persistence.repositories.GoalJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class GoalRepositoryImpl implements GoalRepository {
    @Autowired
    private GoalJpaRepository repository;

    @Override
    public GoalResponse save(GoalResponse goal) {
        var jpaEntity = GoalJpaMapper.toJpa(goal);
        var saved = repository.save(jpaEntity);
        return GoalJpaMapper.toEntity(saved);
    }

    @Override
    public GoalResponse update(GoalResponse goal) {
        var jpaEntity = GoalJpaMapper.toJpa(goal);
        jpaEntity.setId(goal.getId());
        var updated = repository.save(jpaEntity);
        return GoalJpaMapper.toEntity(updated);
    }

    @Override
    public List<GoalResponse> findAll() {
        return repository.findAll().stream()
                .map(GoalJpaMapper::toEntity).collect(Collectors.toList());
    }

    @Override
    public Optional<GoalResponse> findById(UUID id, UUID userId) {
        return repository.findByIdAndUserId(id, userId).map(GoalJpaMapper::toEntity);
    }

    @Override
    public Optional<GoalResponse> findByTitle(String title, UUID userId) {
        return repository.findByTitleAndUserId(title, userId).map(GoalJpaMapper::toEntity);
    }

    @Override
    public List<GoalResponse> findAllByUserId(UUID userId) {
        return repository.findAllByUserId(userId).stream()
                .map(GoalJpaMapper::toEntity).collect(Collectors.toList());
    }

    @Override
    public void delete(GoalResponse goal) {

    }

    @Override
    public List<GoalResponse> findAllByStatus(UUID userId, GoalStatus status) {
        return repository.findAllByStatusAndUserId(status, userId).stream()
                .map(GoalJpaMapper::toEntity).collect(Collectors.toList());
    }
}
