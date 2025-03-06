package com.erosnox.seeurun.application.contracts.gateways;

import com.erosnox.seeurun.domain.entities.GoalEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface GoalRepository {
    GoalEntity save(GoalEntity goal);
    GoalEntity update(GoalEntity goal);
    Optional<GoalEntity> findById(UUID id, UUID userId);
    Optional<GoalEntity> findByTitle(String title, UUID userId);
    List<GoalEntity> findAll(UUID userId);
    void delete(GoalEntity goal);
}
