package com.erosnox.seeurun.infrastructure.mappers;

import com.erosnox.seeurun.domain.entities.GoalEntity;
import com.erosnox.seeurun.infrastructure.persistence.entities.GoalJpaEntity;

public class GoalJpaMapper {
    public static GoalJpaEntity toJpa(GoalEntity goal) {
        var entity = new GoalJpaEntity();
        entity.setTitle(goal.getTitle());
        entity.setDescription(goal.getDescription());
        entity.setTargetDateTime(goal.getTargetDateTime());
        entity.setCompleted(goal.isCompleted());
        entity.setUserId(goal.getUserId());
        entity.setStatus(goal.getStatus());

        return entity;
    }

    public static GoalEntity toEntity(GoalJpaEntity jpaEntity) {
        var entity = new GoalEntity(
                jpaEntity.getTitle(),
                jpaEntity.getDescription(),
                jpaEntity.getTargetDateTime(),
                jpaEntity.getUserId());
        entity.setCompleted(jpaEntity.isCompleted());
        entity.setStatus(jpaEntity.getStatus());
        entity.setId(jpaEntity.getId());
        return entity;
    }
}

