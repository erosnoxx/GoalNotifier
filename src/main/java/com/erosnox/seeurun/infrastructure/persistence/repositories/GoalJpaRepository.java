package com.erosnox.seeurun.infrastructure.persistence.repositories;

import com.erosnox.seeurun.application.enums.GoalStatus;
import com.erosnox.seeurun.infrastructure.persistence.entities.GoalJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface GoalJpaRepository extends JpaRepository<GoalJpaEntity, UUID> {
    @Query("SELECT g FROM GoalJpaEntity g WHERE g.title = :title AND g.userId = :userId")
    Optional<GoalJpaEntity> findByTitleAndUserId(String title, UUID userId);

    List<GoalJpaEntity> findAllByUserId(UUID userId);

    @Query("SELECT g FROM GoalJpaEntity g WHERE g.id = :id AND g.userId = :userId")
    Optional<GoalJpaEntity> findByIdAndUserId(UUID id, UUID userId);

    List<GoalJpaEntity> findAllByStatusAndUserId(GoalStatus status, UUID userId);
}
