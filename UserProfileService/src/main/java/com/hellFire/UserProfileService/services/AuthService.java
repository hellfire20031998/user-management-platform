package com.hellFire.UserProfileService.services;

import com.hellFire.UserProfileService.exceptions.PasswordMisMatchException;
import com.hellFire.UserProfileService.exceptions.UserAlreadyExists;
import com.hellFire.UserProfileService.exceptions.UserNotFoundException;
import com.hellFire.UserProfileService.models.AppUser;
import com.hellFire.UserProfileService.models.dtos.UserProfileDto;
import com.hellFire.UserProfileService.models.requests.SignUpRequest;
import com.hellFire.UserProfileService.models.responses.LoginResponse;
import com.hellFire.UserProfileService.repositories.AppUserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    public AppUserRepository appUserRepository;

    @Autowired
    public PasswordEncoder passwordEncoder;

    @Autowired
    public UserProfileService userProfileService;

    public LoginResponse login(String username, String password) throws UserNotFoundException, PasswordMisMatchException {
        AppUser appUser = appUserRepository.findByUsernameAndDeleted(username, false);
        if (appUser != null) {
            if(passwordEncoder.matches(password, appUser.getPassword())) {
                UserProfileDto userProfileDto = userProfileService.getUserProfileByAppUserId(appUser.getId());
                return new LoginResponse("",userProfileDto);
            }else{
                throw new PasswordMisMatchException("Password doesn't match");
            }
        }else{
            throw new UserNotFoundException("User not found with username: " + username);
        }
    }

    @Transactional
    public LoginResponse signUp(SignUpRequest signUpRequest) throws UserNotFoundException, PasswordMisMatchException, UserAlreadyExists {
        AppUser appUser = appUserRepository.findByUsernameAndDeleted(signUpRequest.getUsername(), false);
        if (appUser != null) {
            throw new UserAlreadyExists("User already exists with username: " + signUpRequest.getUsername());
        }else{
            appUser = new AppUser(null,signUpRequest.getUsername(), passwordEncoder.encode(signUpRequest.getPassword()));
            AppUser savedAppUser = appUserRepository.save(appUser);
            UserProfileDto userProfileDto = userProfileService.createUserProfile(savedAppUser, signUpRequest);
            return new LoginResponse("",userProfileDto);
        }
    }

}
