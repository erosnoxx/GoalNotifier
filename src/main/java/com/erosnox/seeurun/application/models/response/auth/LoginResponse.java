package com.erosnox.seeurun.application.models.response.auth;

import java.util.UUID;

public record LoginResponse(String token, UUID id) {
}
