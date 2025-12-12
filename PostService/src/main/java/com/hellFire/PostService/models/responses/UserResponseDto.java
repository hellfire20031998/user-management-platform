package com.hellFire.PostService.models.responses;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UserResponseDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String phone;
    private String profilePicture;

}