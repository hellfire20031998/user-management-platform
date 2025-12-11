package com.hellFire.UserProfileService.models.requests;

import jakarta.annotation.Nonnull;
import lombok.Data;
import tools.jackson.databind.PropertyNamingStrategies;
import tools.jackson.databind.annotation.JsonNaming;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class LoginRequest {
    @Nonnull
    private String username;
    @Nonnull
    private String password;
}
