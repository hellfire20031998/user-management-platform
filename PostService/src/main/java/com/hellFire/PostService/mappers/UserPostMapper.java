package com.hellFire.PostService.mappers;


import com.hellFire.PostService.models.UserPost;
import com.hellFire.PostService.models.dtos.UserPostDto;
import com.hellFire.PostService.models.requests.CreatePostRequest;
import com.hellFire.PostService.models.requests.UpdatePostRequest;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.control.MappingControl;

import java.util.List;

@Mapper(componentModel = "spring",
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserPostMapper {
    UserPostDto toDto(UserPost userPost);
    UserPost toEntity(UserPostDto userPostDto);
    List<UserPostDto> toListDto(List<UserPost> userPosts);
    UserPost createUserPost(CreatePostRequest createPostRequest);
    void updateUserPost(@MappingTarget UserPost userPost, UpdatePostRequest updatePostRequest);
}
