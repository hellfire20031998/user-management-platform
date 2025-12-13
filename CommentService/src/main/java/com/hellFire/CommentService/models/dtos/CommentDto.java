package com.hellFire.CommentService.models.dtos;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class CommentDto extends BaseEntityDto {
    private Long id;
    private Long userId;
    private Long postId;
    private String comment;
    private String image;
}
