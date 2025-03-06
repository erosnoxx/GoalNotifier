package com.erosnox.seeurun.domain.entities;

import com.erosnox.seeurun.application.enums.RolesEnum;
import com.erosnox.seeurun.domain.entities.common.BaseEntity;
import com.erosnox.seeurun.domain.utils.EntityUtils;

import java.util.UUID;

public class UserEntity extends BaseEntity<UUID> {
    private String username;
    private String passwordHash;
    private RolesEnum role;
    private boolean isActive;

    public UserEntity(
            String username,
            String passwordHash,
            RolesEnum role) {
        setUsername(username);
        setPasswordHash(passwordHash);
        setRole(role);
        setActive(true);
    }

    public void setUsername(String username) {
        if (!EntityUtils.isValidUsername(username)) {
            throw new IllegalArgumentException("Invalid username");
        }
        this.username = username;
    }

    public void setPasswordHash(String passwordHash) {
        if (!EntityUtils.isValidPassword(passwordHash)) {
            throw new IllegalArgumentException("Invalid password");
        }
        this.passwordHash = passwordHash;
    }

    public void setRole(RolesEnum role) {
        this.role = role;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getUsername() {
        return username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public RolesEnum getRole() {
        return role;
    }

    public boolean isActive() {
        return isActive;
    }
}
