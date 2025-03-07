package com.erosnox.seeurun.infrastructure.persistence.entities;

import com.erosnox.seeurun.application.enums.GoalStatus;
import com.erosnox.seeurun.domain.entities.GoalEntity;
import com.erosnox.seeurun.infrastructure.persistence.entities.common.BaseJpaEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity @Table(name = "goals")
@Setter @Getter
@NoArgsConstructor @AllArgsConstructor
public class GoalJpaEntity extends BaseJpaEntity<UUID> {
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String description;
    @Column(name = "target_datetime", nullable = false)
    private LocalDateTime targetDateTime;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private GoalStatus status;
    private boolean completed;

    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id", insertable = false, updatable = false)
    private UserJpaEntity user;
}
