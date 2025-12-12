package com.hellFire.PostService.models.dtos;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.util.Date;


@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class BaseEntityDto {
    private Date createdOn;
    private Date updatedOn;
    private boolean deleted;
}
