package com.hellFire.PostService.models.dtos;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UserPostDto extends BaseEntityDto{
    private Long id;
    private Long userId;
    private String title;
    private String content;
    private String image;
}
