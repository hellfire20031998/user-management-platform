package com.hellFire.UserProfileService.services;

import com.hellFire.UserProfileService.exceptions.PasswordMisMatchException;
import com.hellFire.UserProfileService.exceptions.UserAlreadyExists;
import com.hellFire.UserProfileService.exceptions.UserNotFoundException;
import com.hellFire.UserProfileService.models.AppUser;
import com.hellFire.UserProfileService.models.dtos.UserProfileDto;
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
                throw new PasswordMisMatchException();
            }
        }else{
            throw new UserNotFoundException();
        }
    }

    @Transactional
    public LoginResponse signUp(String username, String password) throws UserNotFoundException, PasswordMisMatchException {
        AppUser appUser = appUserRepository.findByUsernameAndDeleted(username, false);
        if (appUser != null) {
            throw new UserAlreadyExists("User already exists with username: " + username);
        }else{
            appUser = new AppUser(null,username, passwordEncoder.encode(password));
            AppUser savedAppUser = appUserRepository.save(appUser);
            UserProfileDto userProfileDto = userProfileService.createUserProfile(savedAppUser, null);
            return new LoginResponse("",userProfileDto);
        }
    }

}
