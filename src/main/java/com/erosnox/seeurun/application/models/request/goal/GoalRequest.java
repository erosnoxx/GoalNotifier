package com.erosnox.seeurun.application.models.request.goal;

import java.time.LocalDateTime;

public record GoalRequest(
        String title,
        String description,
        LocalDateTime targetDateTime) {
}
