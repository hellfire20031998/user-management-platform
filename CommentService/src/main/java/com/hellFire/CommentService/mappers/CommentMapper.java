package com.hellFire.CommentService.mappers;

import com.hellFire.CommentService.models.Comment;
import com.hellFire.CommentService.models.dtos.CommentDto;
import com.hellFire.CommentService.models.requests.AddCommentRequest;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring",
nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CommentMapper {
    CommentDto toDto(Comment comment);
    Comment toEntity(CommentDto commentDto);

    Comment requestToEntity(AddCommentRequest request, @MappingTarget Comment comment);

    List<CommentDto> toDtoList(List<Comment> comments);
}
