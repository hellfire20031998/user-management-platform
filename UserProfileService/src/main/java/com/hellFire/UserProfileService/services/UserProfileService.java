package com.hellFire.UserProfileService.services;

import com.hellFire.UserProfileService.mappers.UserProfileMapper;
import com.hellFire.UserProfileService.models.AppUser;
import com.hellFire.UserProfileService.models.UserProfile;
import com.hellFire.UserProfileService.models.dtos.UserProfileDto;
import com.hellFire.UserProfileService.models.requests.SignUpRequest;
import com.hellFire.UserProfileService.repositories.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class UserProfileService {

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Autowired
    private UserProfileMapper userProfileMapper;

    public UserProfileDto getUserProfileByAppUserId(Long appUserId) {
        Optional<UserProfile> userProfile = userProfileRepository.findByAppUser_Id(appUserId);
        return userProfile.map(profile -> userProfileMapper.toDto(profile)).orElse(null);
    }

    public UserProfileDto createUserProfile(AppUser appUser, SignUpRequest request) {
        UserProfile userProfile;
        if(Objects.isNull(request)) {
            userProfile = new UserProfile();
        }else {
            userProfile = userProfileMapper.toEntity(request);
        }
        userProfile.setAppUser(appUser);
        return userProfileMapper.toDto(userProfileRepository.save(userProfile));
    }
}
