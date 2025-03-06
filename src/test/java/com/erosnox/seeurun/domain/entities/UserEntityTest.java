package com.erosnox.seeurun.domain.entities;

import com.erosnox.seeurun.application.enums.RolesEnum;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserEntityTest {

    @Test
    void setUsername_ValidUsername_ShouldSetSuccessfully() {
        UserEntity user = new UserEntity(
                "validUser",
                "password123",
                RolesEnum.USER);
        assertEquals("validUser", user.getUsername());
    }

    @Test
    void setUsername_InvalidUsername_ShouldThrowException() {
        assertThrows(IllegalArgumentException.class, () ->
                new UserEntity("ab", "password123", RolesEnum.USER));
        assertThrows(IllegalArgumentException.class, () ->
                new UserEntity("invalid username", "password123",
                        RolesEnum.USER));
    }

    @Test
    void setPasswordHash_ValidPassword_ShouldSetSuccessfully() {
        UserEntity user = new UserEntity("validUser",
                "password123", RolesEnum.USER);
        assertEquals("password123", user.getPasswordHash());
    }

    @Test
    void setPasswordHash_InvalidPassword_ShouldThrowException() {
        assertThrows(IllegalArgumentException.class, () ->
                new UserEntity("validUser", "12345", RolesEnum.USER));
    }

    @Test
    void setRole_ShouldSetSuccessfully() {
        UserEntity user =
                new UserEntity("validUser", "password123",
                        RolesEnum.ADMIN);
        assertEquals(RolesEnum.ADMIN, user.getRole());
    }
}
