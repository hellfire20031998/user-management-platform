package com.hellFire.UserProfileService.mappers;

import com.hellFire.UserProfileService.models.UserProfile;
import com.hellFire.UserProfileService.models.dtos.UserProfileDto;
import com.hellFire.UserProfileService.models.requests.SignUpRequest;
import com.hellFire.UserProfileService.models.requests.UpdateUserProfileRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserProfileMapper {
    UserProfileDto toDto(UserProfile entity);
    UserProfile toEntity(UserProfileDto dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "deleted", constant = "false")
    UserProfile toEntity(SignUpRequest request);

    void updateProfileFromRequest(UpdateUserProfileRequest request, @MappingTarget UserProfile entity);
}
