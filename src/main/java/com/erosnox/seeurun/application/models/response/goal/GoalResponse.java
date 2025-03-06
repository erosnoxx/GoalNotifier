package com.erosnox.seeurun.application.models.response.goal;

import com.erosnox.seeurun.application.models.response.user.UserResponse;

import java.time.LocalDateTime;
import java.util.UUID;

public record GoalResponse(
        UUID id,
        String title,
        String description,
        LocalDateTime targetDateTime,
        boolean completed) {
}
