package com.hellFire.UserProfileService.models.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hellFire.UserProfileService.models.AppUser;
import com.hellFire.UserProfileService.models.responses.LoginResponse;
import jakarta.persistence.*;
import lombok.Data;
import tools.jackson.databind.PropertyNamingStrategies;
import tools.jackson.databind.annotation.JsonNaming;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UserProfileDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String profilePicture;
}
