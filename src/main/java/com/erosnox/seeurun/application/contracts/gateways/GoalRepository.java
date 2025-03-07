package com.erosnox.seeurun.application.contracts.gateways;

import com.erosnox.seeurun.application.enums.GoalStatus;
import com.erosnox.seeurun.domain.entities.GoalResponse;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface GoalRepository {
    GoalResponse save(GoalResponse goal);
    GoalResponse update(GoalResponse goal);
    List<GoalResponse> findAll();
    Optional<GoalResponse> findById(UUID id, UUID userId);
    Optional<GoalResponse> findByTitle(String title, UUID userId);
    List<GoalResponse> findAllByUserId(UUID userId);
    void delete(GoalResponse goal);
    List<GoalResponse> findAllByStatus(UUID userId, GoalStatus status);
}
