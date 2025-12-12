package com.hellFire.UserProfileService.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseEntityDto {
    private Date createdAt;
    private Date updatedAt;
    private Boolean deleted;
}
