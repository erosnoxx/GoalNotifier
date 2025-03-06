package com.erosnox.seeurun.infrastructure.persistence.repositories;

import com.erosnox.seeurun.infrastructure.persistence.entities.UserJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserJpaRepository extends JpaRepository<UserJpaEntity, UUID> {
    UserDetails findByUsername(String username);

    @Query("SELECT u FROM UserJpaEntity u WHERE u.username = :username")
    Optional<UserJpaEntity> findEntityByUsername(@Param("username") String username);
}
