package com.hellFire.UserProfileService.models.requests;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequest {
    @NotNull(message = "username can not be null")
    private String username;
    @NotNull(message = "password can not be null")
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String profilePicture;
}
