package com.hellFire.UserProfileService.services;

import com.hellFire.UserProfileService.exceptions.UserAlreadyExists;
import com.hellFire.UserProfileService.exceptions.UserNotFoundException;
import com.hellFire.UserProfileService.mappers.UserProfileMapper;
import com.hellFire.UserProfileService.models.UserProfile;
import com.hellFire.UserProfileService.models.dtos.UserProfileDto;
import com.hellFire.UserProfileService.models.requests.SignUpRequest;
import com.hellFire.UserProfileService.models.requests.UpdateUserProfileRequest;
import com.hellFire.UserProfileService.repositories.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserProfileService {

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Autowired
    private UserProfileMapper userProfileMapper;

    public UserProfileDto createUserProfile(SignUpRequest request) throws UserAlreadyExists {
       Optional<UserProfile> userProfile = userProfileRepository.findByUsernameAndDeleted(request.getUsername(), false);
       if (userProfile.isPresent()) {
           throw new UserAlreadyExists("User already exists with username: " + request.getUsername());
       }
       return userProfileMapper.toDto(userProfileRepository.save(userProfileMapper.toEntity(request)));
    }

    public UserProfileDto getUserProfileByUserId(Long userId) throws UserNotFoundException {
        Optional<UserProfile> userProfile = userProfileRepository.findById(userId);
        if(userProfile.isPresent()) {
            return userProfileMapper.toDto(userProfile.get());
        }
        throw new UserNotFoundException("user not found with id " + userId);
    }

    public UserProfileDto updateUserProfile(UpdateUserProfileRequest request,Long userId) throws UserNotFoundException {
        Optional<UserProfile> userProfileOptional = userProfileRepository.findById(userId);
        if(userProfileOptional.isPresent()) {
            userProfileMapper.updateProfileFromRequest(request,userProfileOptional.get());
            return userProfileMapper.toDto(userProfileRepository.save(userProfileOptional.get()));
        }
        throw new UserNotFoundException("User not found with id "+ userId);
    }

    public UserProfileDto deleteUserProfile(Long userId) throws UserNotFoundException {
        Optional<UserProfile> userProfileOptional = userProfileRepository.findByIdAndDeleted(userId, false);
        if(userProfileOptional.isPresent()) {
            UserProfile userProfile = userProfileOptional.get();
            userProfile.setDeleted(true);
            return userProfileMapper.toDto(userProfileRepository.save(userProfile));
        }
        throw new UserNotFoundException("User not found with id "+ userId);
    }

}
