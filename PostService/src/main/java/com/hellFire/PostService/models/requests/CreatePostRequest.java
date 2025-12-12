package com.hellFire.PostService.models.requests;


import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CreatePostRequest {
    @NotNull
    private Long userId;
    @NotNull
    private String title;
    private String content;
    private String image;
}
