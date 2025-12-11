package com.hellFire.UserProfileService.mappers;

import com.hellFire.UserProfileService.models.UserProfile;
import com.hellFire.UserProfileService.models.dtos.UserProfileDto;
import com.hellFire.UserProfileService.models.requests.SignUpRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserProfileMapper {
    UserProfileDto toDto(UserProfile entity);
    UserProfile toEntity(UserProfileDto dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "appUser", ignore = true)
    @Mapping(target = "deleted", constant = "false")
    UserProfile toEntity(SignUpRequest request);
}
